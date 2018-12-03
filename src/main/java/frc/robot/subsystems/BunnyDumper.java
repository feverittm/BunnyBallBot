package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 *
 */
public class BunnyDumper extends Subsystem {
	private Solenoid dumper;

	int delayCount=0;
	double totalGatherCurrent = 0.0;

	public BunnyDumper() {
		dumper = new Solenoid(RobotMap.Ports.bunnyDumperSolenod);
	}

	public void initDefaultCommand() {
	}

	public void dumpBunny() {
		dumper.set(true);
	}

	public void updateDashboard() {
	}
}
