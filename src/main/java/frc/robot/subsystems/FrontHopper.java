package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 *
 */
public class FrontHopper extends Subsystem {
	private VictorSPX hopperMotorH;
	private VictorSPX hopperMotorV;

	int delayCount=0;
	double totalHopperCurrent = 0.0;

	public FrontHopper() {
		hopperMotorH = new VictorSPX(RobotMap.Ports.frontHopperMotorPortH);
		hopperMotorV = new VictorSPX(RobotMap.Ports.frontHopperMotorPortV);

        hopperMotorH.setInverted(false);
        hopperMotorH.setNeutralMode(NeutralMode.Coast);
        hopperMotorH.configNominalOutputReverse(0, 10);
        hopperMotorH.configPeakOutputForward(1, 10);
        hopperMotorH.configPeakOutputReverse(-1, 10);
		hopperMotorH.configNominalOutputForward(0, 10);
		
		hopperMotorV.setInverted(false);
        hopperMotorV.setNeutralMode(NeutralMode.Coast);
        hopperMotorV.configNominalOutputReverse(0, 10);
        hopperMotorV.configPeakOutputForward(1, 10);
        hopperMotorV.configPeakOutputReverse(-1, 10);
        hopperMotorV.configNominalOutputForward(0, 10);
	}
		
	public void initDefaultCommand() {
	}

	public void setHopperSpeed(double speedH, double speedV){
		hopperMotorH.set(ControlMode.PercentOutput, speedH);
		hopperMotorV.set(ControlMode.PercentOutput, speedV);
	}

	public void updateDashboard() {
		if (delayCount == 10) {
			SmartDashboard.putNumber("front hopper vertical current", getHopperHCurrent());
			SmartDashboard.putNumber("front hopper horizontal current", getHopperVCurrent());
			delayCount = 0;
		} else {
			delayCount++;
		}		
	}

	public double getHopperVCurrent() {
		double d = Robot.pdp.getCurrent(RobotMap.PDPPorts.frontHopperMotorPortV);
		return d;
	}

	public double getHopperHCurrent() {
		double d = Robot.pdp.getCurrent(RobotMap.PDPPorts.frontHopperMotorPortH);
		return d;
	}
}
