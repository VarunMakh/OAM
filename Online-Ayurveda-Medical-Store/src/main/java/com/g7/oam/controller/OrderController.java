package com.g7.oam.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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

@Validated
@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	IOrderService orderService;

	@PostMapping("/add")
	public ResponseEntity<OrderDTO> addOrder(@RequestBody @Valid Order order) {

		order.setOrderDate(LocalDate.now());
		order.setDispatchDate(order.getOrderDate().plusDays(3));
		Order savedOrder = this.orderService.addOrder(order);
		OrderDTO orderDto = new OrderDTO();
		orderDto.setOrderId(savedOrder.getOrderId());
		orderDto.setOrderDate(savedOrder.getOrderDate());
		List<MedicineDTO> orderMedicineDtoList = new ArrayList<>();
		for (Medicine medicine : savedOrder.getMedicineList()) {
			MedicineDTO medicineDto = new MedicineDTO();
			medicineDto.setMedicineId(medicine.getMedicineId());
			medicineDto.setMedicineName(medicine.getMedicineName());
			medicineDto.setExpiryDate(medicine.getExpiryDate());
			medicineDto.setMedicineCost(medicine.getMedicineCost());
			orderMedicineDtoList.add(medicineDto);
		}
		orderDto.setMedicineDtoList(orderMedicineDtoList);
		orderDto.setDispatchDate(savedOrder.getDispatchDate());
		orderDto.setTotalCost(savedOrder.getTotalCost());

		CustomerDTO customerDto = new CustomerDTO();
		customerDto.setCustomerId(savedOrder.getCustomer().getUserId());
		customerDto.setCustomerName(savedOrder.getCustomer().getCustomerName());

		orderDto.setCustomerDto(customerDto);
		orderDto.setStatus(savedOrder.getStatus());
		return new ResponseEntity<>(orderDto, HttpStatus.OK);

	}

	@PutMapping("/update")
	public ResponseEntity<OrderDTO> updateOrder(@RequestBody @Valid Order order) throws OrderNotFoundException {

		Order updatedOrder = this.orderService.updateOrder(order);
		OrderDTO orderDto = new OrderDTO();
		orderDto.setOrderId(updatedOrder.getOrderId());
		orderDto.setOrderDate(updatedOrder.getOrderDate());
		List<MedicineDTO> orderMedicineDtoList = new ArrayList<>();
		for (Medicine medicine : updatedOrder.getMedicineList()) {
			MedicineDTO medicineDto = new MedicineDTO();
			medicineDto.setMedicineId(medicine.getMedicineId());
			medicineDto.setMedicineName(medicine.getMedicineName());
			medicineDto.setExpiryDate(medicine.getExpiryDate());
			medicineDto.setMedicineCost(medicine.getMedicineCost());
			orderMedicineDtoList.add(medicineDto);
		}
		orderDto.setMedicineDtoList(orderMedicineDtoList);
		orderDto.setDispatchDate(updatedOrder.getDispatchDate());
		orderDto.setTotalCost(updatedOrder.getTotalCost());

		CustomerDTO customerDto = new CustomerDTO();
		customerDto.setCustomerId(updatedOrder.getCustomer().getUserId());
		customerDto.setCustomerName(updatedOrder.getCustomer().getCustomerName());

		orderDto.setCustomerDto(customerDto);
		orderDto.setStatus(updatedOrder.getStatus());
		return new ResponseEntity<>(orderDto, HttpStatus.OK);

	}

	@GetMapping("/view")
	public ResponseEntity<OrderDTO> viewOrder(@RequestBody @Valid Order order) throws OrderNotFoundException {

		Order retrievedOrder = this.orderService.viewOrder(order);
		OrderDTO orderDto = new OrderDTO();
		orderDto.setOrderId(retrievedOrder.getOrderId());
		orderDto.setOrderDate(retrievedOrder.getOrderDate());
		List<MedicineDTO> orderMedicineDtoList = new ArrayList<>();
		for (Medicine medicine : retrievedOrder.getMedicineList()) {
			MedicineDTO medicineDto = new MedicineDTO();
			medicineDto.setMedicineId(medicine.getMedicineId());
			medicineDto.setMedicineName(medicine.getMedicineName());
			medicineDto.setExpiryDate(medicine.getExpiryDate());
			medicineDto.setMedicineCost(medicine.getMedicineCost());
			orderMedicineDtoList.add(medicineDto);
		}
		orderDto.setMedicineDtoList(orderMedicineDtoList);
		orderDto.setDispatchDate(retrievedOrder.getDispatchDate());
		orderDto.setTotalCost(retrievedOrder.getTotalCost());

		CustomerDTO customerDto = new CustomerDTO();
		customerDto.setCustomerId(retrievedOrder.getCustomer().getUserId());
		customerDto.setCustomerName(retrievedOrder.getCustomer().getCustomerName());

		orderDto.setCustomerDto(customerDto);
		orderDto.setStatus(retrievedOrder.getStatus());
		return new ResponseEntity<>(orderDto, HttpStatus.OK);

	}

	@DeleteMapping("/cancel/{orderId}")
	public ResponseEntity<OrderDTO> cancelOrder(@PathVariable("orderId") int orderId) throws OrderNotFoundException {

		Order cancelledOrder = this.orderService.cancelOrder(orderId);
		OrderDTO orderDto = new OrderDTO();
		orderDto.setOrderId(cancelledOrder.getOrderId());
		orderDto.setOrderDate(cancelledOrder.getOrderDate());
		List<MedicineDTO> orderMedicineDtoList = new ArrayList<>();
		for (Medicine medicine : cancelledOrder.getMedicineList()) {
			MedicineDTO medicineDto = new MedicineDTO();
			medicineDto.setMedicineId(medicine.getMedicineId());
			medicineDto.setMedicineName(medicine.getMedicineName());
			medicineDto.setExpiryDate(medicine.getExpiryDate());
			medicineDto.setMedicineCost(medicine.getMedicineCost());
			orderMedicineDtoList.add(medicineDto);
		}
		orderDto.setMedicineDtoList(orderMedicineDtoList);
		orderDto.setDispatchDate(cancelledOrder.getDispatchDate());
		orderDto.setTotalCost(cancelledOrder.getTotalCost());

		CustomerDTO customerDto = new CustomerDTO();
		customerDto.setCustomerId(cancelledOrder.getCustomer().getUserId());
		customerDto.setCustomerName(cancelledOrder.getCustomer().getCustomerName());

		orderDto.setCustomerDto(customerDto);
		orderDto.setStatus(cancelledOrder.getStatus());
		return new ResponseEntity<>(orderDto, HttpStatus.OK);

	}

	@GetMapping("/showAllByMedicineId/{medicineId}")
	public ResponseEntity<List<OrderDTO>> showAllOrders(@PathVariable("medicineId") int medicineid)
			throws MedicineNotFoundException {

		List<Order> ordersByMedicineIdList = this.orderService.showAllOrders(medicineid);
		List<OrderDTO> ordersByMedicineIdDtoList = new ArrayList<>();
		for (Order order : ordersByMedicineIdList) {
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
			ordersByMedicineIdDtoList.add(orderDto);
		}
		return new ResponseEntity<>(ordersByMedicineIdDtoList, HttpStatus.OK);

	}

	@GetMapping("/showAllByCustomer")
	public ResponseEntity<List<OrderDTO>> showAllOrders(@RequestBody @Valid Customer customer)
			throws CustomerNotFoundException {

		List<Order> ordersByCustomerList = this.orderService.showAllOrders(customer);
		List<OrderDTO> ordersByCustomerDtoList = new ArrayList<>();
		for (Order order : ordersByCustomerList) {
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
			ordersByCustomerDtoList.add(orderDto);
		}
		return new ResponseEntity<>(ordersByCustomerDtoList, HttpStatus.OK);

	}

	@GetMapping("/showAllByDate/{orderDate}")
	public ResponseEntity<List<OrderDTO>> showAllOrders(
			@PathVariable("orderDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

		List<Order> ordersByDateList = this.orderService.showAllOrders(date);
		List<OrderDTO> ordersByDateDtoList = new ArrayList<>();
		for (Order order : ordersByDateList) {
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
			ordersByDateDtoList.add(orderDto);
		}
		return new ResponseEntity<>(ordersByDateDtoList, HttpStatus.OK);

	}
	
	

}
