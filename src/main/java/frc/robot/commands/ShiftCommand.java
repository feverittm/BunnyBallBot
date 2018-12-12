package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 *
 */
public class ShiftCommand extends Command {
    private int gear;

    public ShiftCommand() {
        requires(Robot.drivetrain);
        if (Robot.drivetrain.gear == 0) {
            gear = 1;
        } else {
            gear = 0;
        };
    }

    public ShiftCommand(int _gear) {
        requires(Robot.drivetrain);
        gear = _gear;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if( gear == 1 ) {
    		Robot.drivetrain.shift(1);
    	} else {
    		Robot.drivetrain.shift(0);
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
