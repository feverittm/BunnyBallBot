package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 *  Gather Racket balls from the floor.  Single motor attached to a bag motor which is controlled
 *  by a VictorSPX can motor controller.
 */
public class Gatherer extends Subsystem {
	private TalonSRX collectMotor;

	int delayCount=0;
	double collectCurrent = 0.0;
	public boolean state = false;

	public Gatherer() {
		collectMotor = new TalonSRX(RobotMap.Ports.gatherMotorPort);
		collectMotor.setInverted(false);
		collectMotor.setNeutralMode(NeutralMode.Coast);
		collectMotor.configNominalOutputReverse(0, 10);
		collectMotor.configPeakOutputForward(1, 10);
		collectMotor.configPeakOutputReverse(-1, 10);
		collectMotor.configNominalOutputForward(0, 10);
	}

	public void initDefaultCommand() {
	}

	public void setGatherSpeed(double speed) {
		if (speed != 0){
			state = true;
			collectMotor.set(ControlMode.PercentOutput, speed);
		}
	}

	public void stop() {
		collectMotor.set(ControlMode.PercentOutput, 0.0);
		state = false;
	}

	public double getCollectCurrent() {
		collectCurrent = Robot.pdp.getCurrent(RobotMap.PDPPorts.gatherVictor);
		return collectCurrent;
	}

	public void updateDashboard() {
		if (delayCount == 10) {
			SmartDashboard.putNumber("GT - Total Collector current", getCollectCurrent());
			delayCount = 0;
		} else {
			delayCount++;
		}		
	}
}
