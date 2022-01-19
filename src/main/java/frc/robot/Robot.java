package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
	private RobotContainer container;
	private Command autonomousCommand;

	@Override
	public void robotInit() {
		container = new RobotContainer();
	}



	@Override
	public void robotPeriodic() {

		CommandScheduler.getInstance.run();
	}



	@Override
	public void disabledInit() {
		container.getStopDriveCommand().schedule();
	}


	@Override
	public void disabledPeriodic() {
		CommandScheduler.getInstance.run();
	}


	@Override
	public void autonomousInit() {
		autonomousCommand = container.getAutonomousCommand();

		if (autonomousCommand != null) {
			autonomousCommand.schedule();
		}
	}


	@Override
	public void autonomousPeriodic() {
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}


	@Override
	public void teleopInit() {

	}


	@Override
	public void teleopPeriodic() {
		CommandScheduler.getInstance.cancelAll();
	}


	@Override
	public void testPeriodic() {}

}
