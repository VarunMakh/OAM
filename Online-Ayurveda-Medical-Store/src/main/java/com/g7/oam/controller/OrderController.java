package com.g7.oam.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g7.oam.dto.CustomerDTO;
import com.g7.oam.dto.MedicineDTO;
import com.g7.oam.dto.OrderDTO;
import com.g7.oam.entities.Customer;
import com.g7.oam.entities.Medicine;
import com.g7.oam.entities.Order;
import com.g7.oam.exception.CustomerNotFoundException;
import com.g7.oam.exception.MedicineNotFoundException;
import com.g7.oam.exception.OrderNotFoundException;
import com.g7.oam.service.IOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/orders")
@Api(value = "OAM - Order")
public class OrderController {

	@Autowired
	IOrderService orderService;
	Logger logger = org.slf4j.LoggerFactory.getLogger(OrderController.class);

	@PostMapping("/add")
	@ApiOperation(value = "Add Order using Post Mapping", response = Order.class)
	public ResponseEntity<OrderDTO> addOrder(@RequestBody @Valid Order order) {

		logger.info("Add Order Called in Order Controller");
		order.setOrderDate(LocalDate.now());
		order.setDispatchDate(order.getOrderDate().plusDays(3));
		Order savedOrder = this.orderService.addOrder(order);
		return new ResponseEntity<>(convertToDTO(savedOrder), HttpStatus.OK);

	}

	@PutMapping("/update")
	@ApiOperation(value = "Update Order using Put Mapping", response = Order.class)
	public ResponseEntity<OrderDTO> updateOrder(@RequestBody @Valid Order order) throws OrderNotFoundException {

		logger.info("Update Order Called in Order Controller");
		Order updatedOrder = this.orderService.updateOrder(order);
		return new ResponseEntity<>(convertToDTO(updatedOrder), HttpStatus.OK);

	}

	@GetMapping("/view")
	@ApiOperation(value = "View Order using Get Mapping", response = Order.class)
	public ResponseEntity<OrderDTO> viewOrder(@RequestBody @Valid Order order) throws OrderNotFoundException {

		logger.info("View Order Called in Order Controller");
		Order retrievedOrder = this.orderService.viewOrder(order);
		return new ResponseEntity<>(convertToDTO(retrievedOrder), HttpStatus.OK);

	}

	@DeleteMapping("/cancel/{orderId}")
	@ApiOperation(value = "Cancel Order using Delete Mapping", response = Order.class)
	public ResponseEntity<OrderDTO> cancelOrder(@PathVariable("orderId") int orderId) throws OrderNotFoundException {

		logger.info("Cancel Order Called in Order Controller");
		Order cancelledOrder = this.orderService.cancelOrder(orderId);
		return new ResponseEntity<>(convertToDTO(cancelledOrder), HttpStatus.OK);

	}

	@GetMapping("/showAllByMedicineId/{medicineId}")
	@ApiOperation(value = "Show All Orders by Medicine ID using Get Mapping", response = Order.class)
	public ResponseEntity<List<OrderDTO>> showAllOrders(@PathVariable("medicineId") int medicineid)
			throws MedicineNotFoundException {

		logger.info("Show All Orders by Medicine ID Called in Order Controller");
		List<Order> ordersByMedicineIdList = this.orderService.showAllOrders(medicineid);
		List<OrderDTO> ordersByMedicineIdDtoList = new ArrayList<>();
		for (Order order : ordersByMedicineIdList) {
			ordersByMedicineIdDtoList.add(convertToDTO(order));
		}
		return new ResponseEntity<>(ordersByMedicineIdDtoList, HttpStatus.OK);

	}

	@GetMapping("/showAllByCustomer")
	@ApiOperation(value = "Show All Orders by Customer using Get Mapping", response = Order.class)
	public ResponseEntity<List<OrderDTO>> showAllOrders(@RequestBody @Valid Customer customer)
			throws CustomerNotFoundException {

		logger.info("Show All Orders by Customer Called in Order Controller");
		List<Order> ordersByCustomerList = this.orderService.showAllOrders(customer);
		List<OrderDTO> ordersByCustomerDtoList = new ArrayList<>();
		for (Order order : ordersByCustomerList) {
			ordersByCustomerDtoList.add(convertToDTO(order));
		}
		return new ResponseEntity<>(ordersByCustomerDtoList, HttpStatus.OK);

	}

	@GetMapping("/showAllByDate/{orderDate}")
	@ApiOperation(value = "Show All Orders by Order Date using Get Mapping", response = Order.class)
	public ResponseEntity<List<OrderDTO>> showAllOrders(
			@PathVariable("orderDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

		logger.info("Show All Orders by Order Date Called in Order Controller");
		List<Order> ordersByDateList = this.orderService.showAllOrders(date);
		List<OrderDTO> ordersByDateDtoList = new ArrayList<>();
		for (Order order : ordersByDateList) {
			ordersByDateDtoList.add(convertToDTO(order));
		}
		return new ResponseEntity<>(ordersByDateDtoList, HttpStatus.OK);

	}

	public OrderDTO convertToDTO(Order order) {

		logger.info("Convert to DTO Mehod called in Order Controller");
		OrderDTO orderDto = new OrderDTO();

		orderDto.setOrderId(order.getOrderId());
		orderDto.setOrderDate(order.getOrderDate());
		List<MedicineDTO> orderMedicineDtoList = new ArrayList<>();
		for (Medicine medicine : order.getMedicineList()) {
			MedicineDTO medicineDto = new MedicineDTO();
			medicineDto.setMedicineId(medicine.getMedicineId());
			medicineDto.setMedicineName(medicine.getMedicineName());
			medicineDto.setExpiryDate(medicine.getExpiryDate());
			medicineDto.setMedicineCost(medicine.getMedicineCost());
			orderMedicineDtoList.add(medicineDto);
		}
		orderDto.setMedicineDtoList(orderMedicineDtoList);
		orderDto.setDispatchDate(order.getDispatchDate());
		orderDto.setTotalCost(order.getTotalCost());

		CustomerDTO customerDto = new CustomerDTO();
		customerDto.setCustomerId(order.getCustomer().getUserId());
		customerDto.setCustomerName(order.getCustomer().getCustomerName());

		orderDto.setCustomerDto(customerDto);
		orderDto.setStatus(order.getStatus());

		return orderDto;

	}

}
