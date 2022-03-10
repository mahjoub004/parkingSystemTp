package com.parkit.parkingsystem.integration.config;

import com.parkit.parkingsystem.config.DataBaseConfig;
import com.parkit.parkingsystem.constants.DBConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.assertj.core.api.Assertions.assertThat;

public class DataBaseConfigTest {
    private static final Logger logger = LogManager.getLogger("DataBaseConfig");
    DataBaseConfig dataBaseConfig = new DataBaseConfig();

    @Test
    public void getConnectionTest() {

        Connection con = null;

        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(DBConstants.LOOK_FOR_TICKET);
            dataBaseConfig.closePreparedStatement(ps);

        } catch (Exception ex) {
            logger.error("Error comparing ticket", ex);
        } finally {

            dataBaseConfig.closeConnection(con);

        }
        assertThat(con).isNotNull();
    }
}
