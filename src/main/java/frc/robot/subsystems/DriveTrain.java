package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 *
 */
public class DriveTrain extends Subsystem implements IDriveTrain {

	// Include Talon motor controller code from last year's competition robot
	private TalonSRX leftTalon;
	private TalonSRX rightTalon;
	private VictorSPX leftVictor;
	private VictorSPX leftVictor2;
	private VictorSPX rightVictor;
	private VictorSPX rightVictor2;
	private double prevLeftV;
	private double prevRightV;
	
	// Include the shifter solenoid spec from last year's bunnybot.
	public DoubleSolenoid shiftSolenoid;
	int delayCount = 0;
	public int gear = 0;

	// We'll have a NavX Micro gyro.  We should be using the getHeading() method
	// but we are using our own calculation
	private AHRS ahrs;
	private double init_angle;
	public boolean gyropresent = false;

	public boolean decellOn = true; // Default is false.
	public double decellSpeed = 0.2;
	public double decellDivider = 1.2;
	
	public static double totalLeftCurrent;
	public static double totalRightCurrent;

	public DriveTrain() {


		/*
		 *   These are basic TalonSRX references that describe followers and VictorSPX usage:
		 * https://github.com/CrossTheRoadElec/Phoenix-Examples-Languages/blob/master/Java/SixTalonArcadeDrive
		 * https://github.com/CrossTheRoadElec/Phoenix-Examples-Languages/blob/master/Java/VelocityClosedLoop
		 *
		 *   Our main java drive train code from last year's competition robot that used similar motor controllers:
		 * https://github.com/Team997Coders/2018PowerUp/blob/master/src/main/java/frc/team997/robot/subsystems/DriveTrain.java
		 *
		 *   This is the drive train code from last year's Bunnybot that had shifters:
		 * https://github.com/Team997Coders/BunnyBotElmer/blob/master/src/main/java/frc/team997/robot/subsystems/DriveTrain.java
		 */
		
		leftTalon = new TalonSRX(RobotMap.Ports.leftTalonPort);
		rightTalon = new TalonSRX(RobotMap.Ports.rightTalonPort);
		leftVictor = new VictorSPX(RobotMap.Ports.leftVictorPort);
		leftVictor2 = new VictorSPX(RobotMap.Ports.leftVictorPort2);
		rightVictor = new VictorSPX(RobotMap.Ports.rightVictorPort);
		rightVictor2 = new VictorSPX(RobotMap.Ports.rightVictorPort2);

		leftVictor.follow(leftTalon);
		leftVictor2.follow(leftTalon);
		rightVictor.follow(rightTalon);
		rightVictor2.follow(rightTalon);
		
		/* drive robot forward and make sure all 
		 * motors spin the correct way.
		 * Toggle booleans accordingly.... */
		leftTalon.setInverted(false);
		leftVictor.setInverted(false);
		leftVictor2.setInverted(false);
		
		rightTalon.setInverted(true);
		rightVictor.setInverted(true);
		rightVictor2.setInverted(true);

		/*
		 * CTRE encoder on each Talon on the drivetrain, mechanically connected to the front wheels (i.e. 1:1 ratio)
		 */
		leftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		rightTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		leftTalon.setSensorPhase(true);
		rightTalon.setSensorPhase(true);
		
		leftTalon.setNeutralMode(NeutralMode.Coast);
		rightTalon.setNeutralMode(NeutralMode.Coast);
		
		/* set the peak, nominal outputs */
		leftTalon.configNominalOutputForward(0, 10);
		leftTalon.configNominalOutputReverse(0, 10);
		//leftTalon.configPeakOutputForward(1, 10);	//Use for PB
		//leftTalon.configPeakOutputReverse(-1, 10); //Use for PB
		leftTalon.configPeakOutputForward(0.6, 10);	//Use for extrasensitive CB
		leftTalon.configPeakOutputReverse(-0.6, 10); //Use for extrasensitive CB
		
		leftTalon.enableCurrentLimit(true);
		leftTalon.configPeakCurrentLimit(40, 10);
		leftTalon.configPeakCurrentDuration(100, 10);
		leftTalon.configContinuousCurrentLimit(30, 10);
		
		rightTalon.configNominalOutputForward(0, 10);
		rightTalon.configNominalOutputReverse(0, 10);
		//rightTalon.configPeakOutputForward(1, 10); //Use for PB
		//rightTalon.configPeakOutputReverse(-1, 10); //Use for PB
		rightTalon.configPeakOutputForward(0.6, 10);  //Use for extrasensitive CB
		rightTalon.configPeakOutputReverse(-0.6, 10); //Use for extrasensitive CB
		
		rightTalon.enableCurrentLimit(true);
		rightTalon.configPeakCurrentLimit(40, 10);
		rightTalon.configPeakCurrentDuration(100, 10);
		rightTalon.configContinuousCurrentLimit(30, 10);
		
		leftTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 40, 10);
		//leftTalon.configOpenloopRamp(0.25, 10);
		rightTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 40, 10);
		//rightTalon.configOpenloopRamp(0.25, 10);
		
		/* set closed loop gains in slot0 */
		leftTalon.config_kF(0, 0.1097, 10);
		leftTalon.config_kP(0, 0.113333, 10);
		leftTalon.config_kI(0, 0, 10);
		leftTalon.config_kD(0, 0, 10);		

		rightTalon.config_kF(0, 0.1097, 10);
		rightTalon.config_kP(0, 0.113333, 10);
		rightTalon.config_kI(0, 0, 10);
		rightTalon.config_kD(0, 0, 10);	
		
		new SensorCollection(leftTalon);
		new SensorCollection(rightTalon);

		/*
		 * Set-up the gyro
		 */
		try {
			ahrs = new AHRS(RobotMap.Ports.AHRS);
			System.out.println("ahrs is cool!");
			ahrs.reset();
			init_angle = ahrs.getAngle();
			gyropresent = true;
		} catch (RuntimeException e) {
			System.out.println("DT - The AHRS constructor do a bad.");
		}
		
    	//Motor.changeControlMode(TalonControlMode.PercentVbus);
		leftTalon.setSelectedSensorPosition(0, 0, 10);
		rightTalon.setSelectedSensorPosition(0, 0, 10);
    	leftTalon.set(ControlMode.PercentOutput, 0.0);
    	rightTalon.set(ControlMode.PercentOutput, 0.0);

		decellSpeed = 0.2;
		decellDivider = 3;

		gear = 0;
    	shiftSolenoid.set(DoubleSolenoid.Value.kForward);
    	//this.shift(0);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new TankDrive());
		//setDefaultCommand(new ArcadeDrive());
	}

	private double getDecell(double velocity, double prevVelocity) { // copied from 2017 :I

		if (!decellOn) {
			return velocity;
		} else {

			if ((velocity >= 0 && prevVelocity >= 0) || (velocity <= 0 && prevVelocity <= 0)) {
				prevVelocity = velocity;
			} else {

				if (Math.abs(prevVelocity) <= decellSpeed) {
					prevVelocity = velocity;
				} else {
					prevVelocity = prevVelocity / decellDivider;
				}

			}

			return prevVelocity;
		}
	}

	public void driveDecell(double leftSpeed, double rightSpeed) {
		prevLeftV = getDecell(leftSpeed, prevLeftV);
		leftTalon.set(ControlMode.PercentOutput, prevLeftV);
		
		prevRightV = getDecell(rightSpeed, prevRightV);
		rightTalon.set(ControlMode.PercentOutput, prevRightV);
		
	}

	public double getLeftMasterVoltage() {
		return leftTalon.getMotorOutputVoltage();
	}

	public double getRightMasterVoltage() {
		return rightTalon.getMotorOutputVoltage();
	}
	
	public void setVoltages(double leftSpeed, double rightSpeed) {
		leftTalon.set(ControlMode.PercentOutput, leftSpeed);
		rightTalon.set(ControlMode.PercentOutput, rightSpeed);
	}

	public double getLeftEncoderTicks() {
		/* CTRE Magnetic Encoder relative, same as Quadrature */
		leftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0); /* PIDLoop=0,timeoutMs=0 */
		return leftTalon.getSelectedSensorPosition(0);
	}

	public double getRightEncoderTicks() {
		rightTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0); /* PIDLoop=0,timeoutMs=0 */
		return rightTalon.getSelectedSensorPosition(0);
	}

	public double getLeftEncoderRate() {
		return leftTalon.getSelectedSensorVelocity(0);
	}

	public double getRightEncoderRate() {
		return rightTalon.getSelectedSensorVelocity(0);
	}

	public void resetEncoders() {
		leftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0); /* PIDLoop=0,timeoutMs=0 */
		leftTalon.setSelectedSensorPosition(0, 0, 10);
		rightTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0); /* PIDLoop=0,timeoutMs=0 */
		rightTalon.setSelectedSensorPosition(0, 0, 10);
		System.out.println("Encoders reset!");
	}
	
	public double getHeading() {
		//ahrs.getFusedHeading()
		if (gyropresent) {
			return( ahrs.getAngle() - init_angle );
		} else {
			return 0.0;
		}
	}
	
	public double getAHRSAngle() {
		if (gyropresent) {
			return ahrs.getAngle();
		} else {
			return 0.0;
		}
	}

	public double getleftVelocity() {
		return leftTalon.getSelectedSensorVelocity(0);
	}
	
	public double getrightvelocity() {
		return rightTalon.getSelectedSensorVelocity(0);
	}
	
	public void setBrake() {
		leftTalon.setNeutralMode(NeutralMode.Brake);
		rightTalon.setNeutralMode(NeutralMode.Brake);
		
		leftVictor.setNeutralMode(NeutralMode.Brake);
		rightVictor.setNeutralMode(NeutralMode.Brake);
		leftVictor2.setNeutralMode(NeutralMode.Brake);
		rightVictor2.setNeutralMode(NeutralMode.Brake);
	}
	
	public void setCoast() {
		leftTalon.setNeutralMode(NeutralMode.Coast);
		rightTalon.setNeutralMode(NeutralMode.Coast);
		
		leftVictor.setNeutralMode(NeutralMode.Coast);
		rightVictor.setNeutralMode(NeutralMode.Coast);
		leftVictor2.setNeutralMode(NeutralMode.Coast);
		rightVictor2.setNeutralMode(NeutralMode.Coast);
	}

	public void shift(int g) {
    	if (gear != g && gear != 0) {
    		shiftSolenoid.set(DoubleSolenoid.Value.kForward);
    		gear = 0;
    	} else if (gear != g) {
    		shiftSolenoid.set(DoubleSolenoid.Value.kReverse);
    		gear = 1;
    	}
    }

	public void updateDashboard() {
		if (delayCount == 10) {
			SmartDashboard.putNumber("DT - Left master voltage", leftTalon.getMotorOutputVoltage());
			SmartDashboard.putNumber("DT - Right master voltage", rightTalon.getMotorOutputVoltage());
			SmartDashboard.putNumber("DT - Left Encoder", getLeftEncoderTicks());
			SmartDashboard.putNumber("DT - Right Encoder", getRightEncoderTicks());
			SmartDashboard.putNumber("DT - Left Encoder distance in inches", getLeftEncoderTicks()*RobotMap.Values.inchesPerTick);
			SmartDashboard.putNumber("DT - Right Encoder distance in inches", getRightEncoderTicks()*RobotMap.Values.inchesPerTick);
			SmartDashboard.putNumber("DT - Left Encoder Velocity", leftTalon.getSelectedSensorVelocity(0));
			SmartDashboard.putNumber("DT - Right EncoderVelocity", rightTalon.getSelectedSensorVelocity(0));
			SmartDashboard.putNumber("DT - Heading", getHeading());
			SmartDashboard.putNumber("DT - Total Left Current", getTotalLeftCurrent());
			SmartDashboard.putNumber("DT - Total Right Current", getTotalRightCurrent());
			SmartDashboard.putBoolean("Decell on?", decellOn);

			delayCount = 0;
		} else {
			delayCount++;
		}		
	}

	public double getTotalLeftCurrent() {
		totalLeftCurrent = (Robot.pdp.getCurrent(RobotMap.PDPPorts.leftDriveTrainTalon)
					+ Robot.pdp.getCurrent(RobotMap.PDPPorts.leftDriveTrain)
					+ Robot.pdp.getCurrent(RobotMap.PDPPorts.leftDriveTrain2));
		return totalLeftCurrent;
	}

	public double getTotalRightCurrent() {
		
		totalRightCurrent = (Robot.pdp.getCurrent(RobotMap.PDPPorts.rightDriveTrainTalon)
				+ Robot.pdp.getCurrent(RobotMap.PDPPorts.rightDriveTrain)
				+ Robot.pdp.getCurrent(RobotMap.PDPPorts.rightDriveTrain2));
		return totalRightCurrent;
	}

	public void safeVoltages(double leftSpeed, double rightSpeed) {

		if (getTotalLeftCurrent() > RobotMap.Values.drivetrainLeftLimit) {
			leftTalon.set(ControlMode.PercentOutput, 0);
		} else {
			leftTalon.set(ControlMode.PercentOutput, leftSpeed);
		}

		if (getTotalRightCurrent() > RobotMap.Values.drivetrainRightLimit) {
			rightTalon.set(ControlMode.PercentOutput, 0);
		} else {
			rightTalon.set(ControlMode.PercentOutput, rightSpeed);
		}
	}
}
