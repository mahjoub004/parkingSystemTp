package com.parkit.parkingsystem.integration.dao;


import com.parkit.parkingsystem.config.DataBaseConfig;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.FareCalculatorService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 *
 */
public class ParkingSpotDaoTest {
    private static final Logger logger = LogManager.getLogger("DataBaseConfig");
    private static final DataBaseConfig dataBaseConfig = new DataBaseConfig();
    private static  FareCalculatorService fareCalculatorService;
    private static ParkingSpotDAO parkingSpotDAO;
    private TicketDAO ticketDAO;
    private Ticket ticket;

    @Test
    public void updateParkingTest() {
        ParkingType parkingType = ParkingType.CAR;
        ParkingSpot parkingSpot = new ParkingSpot(3, parkingType, false);
        assertEquals(ParkingSpotDAO.updateParking(parkingSpot), true);


    }
}