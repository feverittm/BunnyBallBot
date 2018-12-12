/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class AutoDriveForward extends PIDCommand {
  private double distance;

  public AutoDriveForward( double d ) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    super(RobotMap.Values.driveDistanceP, RobotMap.Values.driveDistanceI, RobotMap.Values.driveDistanceD);
    distance = d;
    requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("Init. AutoDriveForward");
    getPIDController().setPID(RobotMap.Values.driveDistanceP, RobotMap.Values.driveDistanceI, RobotMap.Values.driveDistanceD);
    setSetpoint(distance);
    this.getPIDController().enable();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  @Override
  protected double returnPIDInput() {
    return Robot.drivetrain.getLeftEncoderTicks();
  }

  @Override
  protected void usePIDOutput(double speed) {
    Robot.drivetrain.setVoltages(speed, speed);
  }

  @Override 
  protected boolean isFinished() {
    return getPIDController().onTarget();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
