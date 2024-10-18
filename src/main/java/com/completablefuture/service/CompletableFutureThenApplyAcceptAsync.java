package com.completablefuture.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.completablefuture.repository.EmployeeJsonRepository;

@Service
public class CompletableFutureThenApplyAcceptAsync {

	@Autowired
	EmployeeJsonRepository jsonRepo;
	
	/*
	 *  joining the completable future using thenApply, thenAccept
	 */
	
	public Void getAllEmployeesFromDB() throws InterruptedException, ExecutionException {
		
		CompletableFuture<Void> future= CompletableFuture.supplyAsync(()->{
			System.out.println("Thread for getAllEmployees: "+Thread.currentThread().getName());
			return jsonRepo.findAll();
			
		}).thenApply((emp)->{ 
			System.out.println("Thread for filterNewEmployees: "+Thread.currentThread().getName());
			return emp.stream()
					.filter(x->"TRUE".equalsIgnoreCase(x.getNewJoiner()))
					.collect(Collectors.toList());
		}).thenApply((emp)->{
			System.out.println("Thread for filter learning pending: "+Thread.currentThread().getName());
			return emp.stream().filter(x->"TRUE".equalsIgnoreCase(x.getLearningPending())).collect(Collectors.toList());
		}).thenApply((emp)->{
			System.out.println("Thread for get email List: "+Thread.currentThread().getName());
			return emp.stream().map(x->x.getEmail()).collect(Collectors.toList());
		}).thenAccept((emp)-> {
			System.out.println("Thread for send email: "+Thread.currentThread().getName());
			emp.forEach(em-> sendMail(em));
		});
		return future.get();
	}
	
	/*
	 *  joining the completable future using thenApplyAsync, thenAcceptAsync and added custom Executor
	 */
	
public Void getAllEmployeesFromDBUsingAsyncAndExecutor() throws InterruptedException, ExecutionException {
		Executor executor = Executors.newFixedThreadPool(5);
		CompletableFuture<Void> future= CompletableFuture.supplyAsync(()->{
			System.out.println("Thread for getAllEmployees: "+Thread.currentThread().getName());
			return jsonRepo.findAll();
			
		},executor).thenApplyAsync((emp)->{ 
			System.out.println("Thread for filterNewEmployees: "+Thread.currentThread().getName());
			return emp.stream()
					.filter(x->"TRUE".equalsIgnoreCase(x.getNewJoiner()))
					.collect(Collectors.toList());
		},executor).thenApplyAsync((emp)->{
			System.out.println("Thread for filter learning pending: "+Thread.currentThread().getName());
			return emp.stream().filter(x->"TRUE".equalsIgnoreCase(x.getLearningPending())).collect(Collectors.toList());
		},executor).thenApplyAsync((emp)->{
			System.out.println("Thread for get email List: "+Thread.currentThread().getName());
			return emp.stream().map(x->x.getEmail()).collect(Collectors.toList());
		},executor).thenAcceptAsync((emp)-> {
			System.out.println("Thread for send email: "+Thread.currentThread().getName());
			emp.forEach(em-> sendMail(em));
		});
		return future.get();
	}
	
	public void sendMail(String email) {
		System.out.println("Notified employees to complete the learning : "+email);
	}
}
