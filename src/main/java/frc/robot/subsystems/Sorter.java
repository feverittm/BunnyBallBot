package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 *
 */
public class Sorter extends Subsystem {
	private TalonSRX sortMotor;
	private Solenoid sortSolenoid;
	private DigitalInput ballSensor;

	public double sortCurrent = 0;

	public Sorter() {
		sortMotor = new TalonSRX(RobotMap.Ports.sortTalon);
		sortMotor.setInverted(false);
		sortMotor.setNeutralMode(NeutralMode.Coast);
		sortMotor.configNominalOutputReverse(0, 10);
		sortMotor.configPeakOutputForward(1, 10);
		sortMotor.configPeakOutputReverse(-1, 10);
		sortMotor.configNominalOutputForward(0, 10);
		
		sortMotor.configPeakCurrentLimit((int)(RobotMap.Values.sorterCurrentLimit + 10), 10);
		sortMotor.configPeakCurrentDuration(100, 10);
		sortMotor.configContinuousCurrentLimit((int)RobotMap.Values.sorterCurrentLimit, 10);
		sortMotor.enableCurrentLimit(true);

		sortSolenoid = new Solenoid(RobotMap.Ports.sortSolenoid);
		sortSolenoid.set(false);

		ballSensor = new DigitalInput(RobotMap.Ports.ballSensor);
	}

	public void initDefaultCommand() {
	}

	public void setSortSpeed(double speed) {
		sortMotor.set(ControlMode.PercentOutput, speed);
	}

	public void ejectBall() {
		double t = System.currentTimeMillis();
		double s = sortMotor.getMotorOutputPercent();
		sortSolenoid.set(true);
		while(System.currentTimeMillis() - t < RobotMap.Values.ejectTime) {
			sortMotor.set(ControlMode.PercentOutput, 0);
		}
		sortSolenoid.set(false);
		sortMotor.set(ControlMode.PercentOutput, s);
	}

	public boolean getBallSensor() {
		return ballSensor.get();
	}

	public boolean checkforJam() {
		sortCurrent = sortMotor.getOutputCurrent();
		if ( sortCurrent > RobotMap.Values.sorterCurrentLimit && sortMotor.getMotorOutputVoltage() > 1.0) {
			return true;
		}
		return false;
	}

	public void updateDashboard() {
		SmartDashboard.putNumber("SORT - Sorter Current", getSorterCurrent());
		SmartDashboard.putBoolean("SORT - Ejecter State", sortSolenoid.get());
	}

	public double getSorterCurrent() {
		return Robot.pdp.getCurrent(RobotMap.PDPPorts.sorterMotorPort);
	}
}
