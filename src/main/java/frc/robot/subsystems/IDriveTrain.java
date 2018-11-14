package frc.robot.subsystems;

/**
 *
 */
public interface IDriveTrain {

	public void initDefaultCommand();

	public void driveDecell(double leftSpeed, double rightSpeed);

	public double getLeftMasterVoltage();

	public double getRightMasterVoltage();
	
	public void setVoltages(double leftSpeed, double rightSpeed);

	public double getLeftEncoderTicks();

	public double getRightEncoderTicks();

	public double getLeftEncoderRate();

	public double getRightEncoderRate();

	public void resetEncoders();
	
	public double getHeading();
	
	public double getAHRSAngle();

	public double getleftVelocity();
	
	public double getrightvelocity();
	
	public void setBrake();
	
	public void setCoast();

	public void shift(int g);

	public void updateDashboard();

	public double getTotalLeftCurrent();

	public double getTotalRightCurrent();

	public void safeVoltages(double leftSpeed, double rightSpeed);
}
