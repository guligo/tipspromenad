package co.vrings.services;

import java.util.Date;

/**
 * Interface of service layer component that provides reporting functions.
 * 
 * @author guligo
 */
public interface StatisticsService {
	
	public int getWorkoutsTotal(String username, Date start, Date end);
	
	public int getWorkoutsTotal(String username, int month);
	
}
