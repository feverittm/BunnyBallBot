package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 *
 */
public class BunnyDumper extends Subsystem {
	private VictorSP collectMotor;

	int delayCount=0;
	double totalGatherCurrent = 0.0;

	public BunnyDumper() {
		collectMotor = new VictorSP(RobotMap.Ports.gatherVictor);
	}

	public void initDefaultCommand() {
	}

	public void setGatherSpeed(double speed) {
		collectMotor.set(speed);
	}

	public void updateDashboard() {
		if (delayCount == 10) {
			SmartDashboard.putNumber("total gatherer current", getGatherCurrent());
			delayCount = 0;
		} else {
			delayCount++;
		}		
	}

	public double getGatherCurrent() {
		double d = Robot.pdp.getCurrent(RobotMap.PDPPorts.gatherVictor);
		return d;
	}
}
