package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 *
 */
public class BunnyDumper extends Subsystem {
	private Solenoid dumper;

	public BunnyDumper() {
		dumper = new Solenoid(RobotMap.Ports.bunnyDumperSolenod);
	}

	public void initDefaultCommand() {
	}

	public void dumpBunny() {
		dumper.set(true);
	}

	public void returnBunny() {
		dumper.set(false);
	}

	public void updateDashboard() {
	}
}
