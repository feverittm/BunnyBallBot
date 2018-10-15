package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

/**
 *
 */
public class ShiftCommand extends Command {

    public ShiftCommand() {
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.drivetrain.gear == 1) {
    		Robot.drivetrain.shift(0);
    	} else if(Robot.drivetrain.gear == 0) {
    		Robot.drivetrain.shift(1);
    	} else {
    		SmartDashboard.putNumber("Oh Noes! driveTrain shifter expected 1 or 0 at Robot.driveTrain.gear, "
    				+ "got this instead.", Robot.drivetrain.gear);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end(); //might be a problem when driving
    }
}
