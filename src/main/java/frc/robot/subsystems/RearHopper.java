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
	private VictorSP rearHopperMotor;

	int delayCount=0;
	double totalGatherCurrent = 0.0;

	public RearHopper() {
		rearHopperMotor = new VictorSP(RobotMap.Ports.rearHopperPWM);
	}

	public void initDefaultCommand() {
	}

	public void setGatherSpeed(double speed) {
		rearHopperMotor.set(speed);
	}

	public void updateDashboard() {
	}
}
