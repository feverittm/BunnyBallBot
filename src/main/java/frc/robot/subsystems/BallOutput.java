package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 *  Gather Racket balls from the floor.  Single motor attached to a bag motor which is controlled
 *  by a VictorSPX can motor controller.
 */
public class BallOutput extends Subsystem {
	private VictorSP outputMotor;

	int delayCount=0;
	double outputCurrent = 0.0;
	public boolean state = false;

	public BallOutput() {
		outputMotor = new VictorSP(RobotMap.Ports.ballOutputPWM);
		outputMotor.setInverted(false);
	}

	public void initDefaultCommand() {
	}

	public void setOutputSpeed(double speed) {
		if (speed > 0) {
			state = true;
		}
		outputMotor.set(speed);
	}

	public void stop() {
		outputMotor.set(0.0);
		state = false;
	}

	public double getOutputCurrent() {
		outputCurrent = Robot.pdp.getCurrent(RobotMap.PDPPorts.ballOutputMotorPort);
		return outputCurrent;
	}
}
