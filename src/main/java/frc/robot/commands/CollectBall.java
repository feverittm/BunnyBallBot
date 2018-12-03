/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 *  Toggle the gatherer on/off
 */
public class CollectBall extends Command {
  double speed = 0;
	
    public CollectBall() {
      requires(Robot.gather);
      this.speed = 1.0;
    }

    public CollectBall(double speed) {
      requires(Robot.gather);
      speed = this.speed;
    }

    protected void initialize() {
    }

    protected void execute() {
      if (this.speed == 0) {
        Robot.gather.state = false;
        Robot.gather.stop();
      } else {
        Robot.gather.state = true;
        Robot.gather.setGatherSpeed(speed);
      }
    }

    protected boolean isFinished() {
        return true;
    }
    
    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
}

