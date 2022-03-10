package com.parkit.parkingsystem.integration.dao;

import com.parkit.parkingsystem.config.DataBaseConfig;
import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.FareCalculatorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TicketDaoTest {
    private static final Logger logger = LogManager.getLogger("DataBaseConfig");
    private static final DataBaseConfig dataBaseConfig = new DataBaseConfig();
    private static FareCalculatorService fareCalculatorService ;

    private TicketDAO ticketDAO ;
    private Ticket ticket;


    //GET_TICKET = "select t.PARKING_NUMBER, t.ID, t.PRICE, t.IN_TIME, t.OUT_TIME, p.TYPE from ticket t,parking p where p.parking_number = t.parking_number and t.VEHICLE_REG_NUMBER=? order by t.IN_TIME  limit 1";
    @Test
    public void getTicketTest() {
        ticket = new Ticket();
        Date inTime = new Date();
        inTime.setTime(System.currentTimeMillis() - (60 * 60 * 1000));
        Date outTime = new Date();
        ParkingSpot parkingSpot = new ParkingSpot(4, ParkingType.CAR, true);
        ticket.setInTime(inTime);
        ticket.setOutTime(outTime);
        ticket.setParkingSpot(parkingSpot);
        ticket.setVehicleRegNumber("MAC12345");

        ticketDAO = new TicketDAO();
        ticketDAO.saveTicket(ticket);

        Ticket reslt = ticketDAO.getTicket(ticket.getVehicleRegNumber());
        reslt.equals(ticketDAO.getTicket("RS12345"));

    }

    @Test
    public void saveTicketTest(){
        ticket = new Ticket();
        Date inTime = new Date();
        inTime.setTime(System.currentTimeMillis() - (60 * 60 * 1000));
        Date outTime = new Date();
        ParkingSpot parkingSpot = new ParkingSpot(3, ParkingType.CAR, true);
        ticket.setInTime(inTime);
        ticket.setOutTime(outTime);
        ticket.setParkingSpot(parkingSpot);
        ticket.setVehicleRegNumber("GR-23-BS");

        ticketDAO = new TicketDAO();
        ticketDAO.saveTicket(ticket);
        Ticket result =  ticketDAO.getTicket("GR-23-BS");
        System.out.println(result.getVehicleRegNumber());

    }
    @Test
    public void updateTicketTest(){
        ticket = new Ticket();
        Date inTime = new Date();
        inTime.setTime(System.currentTimeMillis() - (60 * 60 * 1000));
        Date outTime = new Date();
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, true);
        ticket.setInTime(inTime);
        ticket.setOutTime(outTime);
        ticket.setParkingSpot(parkingSpot);
        ticket.setVehicleRegNumber("BIKE 14-14");
        ticket.setPrice(12);
        //
        ticketDAO = new TicketDAO();
        ticketDAO.saveTicket(ticket);
        //
        ticket.setInTime(inTime);
        ticket.setOutTime(outTime);
        ParkingSpot parkingSpot2 = new ParkingSpot(3, ParkingType.CAR, true);
        ticket.setParkingSpot(parkingSpot2);
        ticket.setVehicleRegNumber("BIKE 15-15");
        ticket.setPrice(20);

         ticketDAO.updateTicket(ticket);
         ticketDAO.saveTicket(ticket);

        Ticket result =  ticketDAO.getTicket("BIKE 13-13");
        System.out.println(result.getVehicleRegNumber());
    }

    @Test
    public void compareTicketTest() {

        fareCalculatorService = new FareCalculatorService(ticketDAO);
        ticket = new Ticket();
        Date inTime = new Date();
        inTime.setTime(System.currentTimeMillis() - (60 * 60 * 1000));
        Date outTime = new Date();
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.BIKE, false);
        ticket.setInTime(inTime);
        ticket.setOutTime(outTime);
        ticket.setParkingSpot(parkingSpot);
        fareCalculatorService.calculateFare(ticket);
        assertEquals (ticket.getPrice(), Fare.BIKE_RATE_PER_HOUR);
    }

}
