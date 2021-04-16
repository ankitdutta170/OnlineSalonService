package com.cg.trg.boot.salon.dao;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.trg.boot.salon.bean.Appointment;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {
	@Query("select appointment from Appointment appointment where user_id_fk = :userId")
	public List<Appointment> getAppointmentByCustomer(@Param("userId")long userId);
	@Query("select appointment from Appointment appointment where salon_service_id_fk= :id")
	public List<Appointment> getCountofAppointmentsOfService(@Param("id")long id);
	//@Query("select appointment from Appointment appointment where appointment.preferred_date:date")
	public List<Appointment> findByPreferredDate(LocalDate date);
	/*
	 * public Appointment addAppointment(Appointment appointment); public
	 * Appointment removeAppointment(long id); public Appointment
	 * updateAppointment(long id, Appointment appointment); public Appointment
	 * getAppointment(long id); public List<Appointment> getAllAppointments();
	 * public List<Appointment> getOpenAppointments();
	 */
}
