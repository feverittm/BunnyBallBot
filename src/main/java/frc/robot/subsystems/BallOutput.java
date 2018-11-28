package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 *  Gather Racket balls from the floor.  Single motor attached to a bag motor which is controlled
 *  by a VictorSPX can motor controller.
 */
public class BallOutput extends Subsystem {
	private VictorSP rearMotor;
	private VictorSP outputMotor;

	int delayCount=0;
	double outputCurrent = 0.0;
	public boolean state = false;

	public BallOutput() {
		rearMotor = new VictorSP(RobotMap.Ports.rearHopperPWM);
		rearMotor.setInverted(false);
		outputMotor = new VictorSP(RobotMap.Ports.ballOutputPWM);
		outputMotor.setInverted(false);
	}

	public void initDefaultCommand() {
	}

	public void setRearHopperSpeed(double speed) {
		rearMotor.set(speed);
	}

	public void setOutputSpeed(double speed) {
		outputMotor.set(speed);
	}

	public void stop() {
		outputMotor.set(0.0);
		rearMotor.set(0.0);
		state = false;
	}

	public double getOutputCurrent() {
		outputCurrent = Robot.pdp.getCurrent(RobotMap.PDPPorts.ballOutput);
		return outputCurrent;
	}

	public void updateDashboard() {
		if (delayCount == 10) {
			SmartDashboard.putNumber("Ball Output current", getOutputCurrent());
			delayCount = 0;
		} else {
			delayCount++;
		}		
	}
}
