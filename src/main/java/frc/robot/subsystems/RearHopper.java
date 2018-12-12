package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 *
 */
public class RearHopper extends Subsystem {
	private VictorSP hopperMotor;

	private int delayCount=0;
	public boolean state = false;

	public RearHopper() {
		hopperMotor = new VictorSP(RobotMap.Ports.rearHopperPWM);
        hopperMotor.setInverted(false);
	}
		
	public void initDefaultCommand() {
	}

	public void setHopperSpeed(double speed){
		if (speed > 0) {
			state = true;
		}
		hopperMotor.set(speed);
	}

	public void stop() {
		state = false;
		setHopperSpeed(0);
	}

	public void updateDashboard() {
		if (delayCount == 10) {
			SmartDashboard.putBoolean("Rear Hopper Running?", state);
			SmartDashboard.putNumber("Rear Hopper Current", getHopperCurrent());

			delayCount = 0;
		} else {
			delayCount++;
		}		
	}

	public double getHopperCurrent() {
		double d = Robot.pdp.getCurrent(RobotMap.PDPPorts.rearHopperMotorPort);
		return d;
	}
}
