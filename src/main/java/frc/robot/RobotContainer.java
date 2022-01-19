package frc.robot;
ö
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.DrivetrainSubsystem;

public class RobotContainer {

	// subsystems
	private final DrivetrainSubsystem drivetrain = DrivetrainSubsystem.getInstance();

	//controllers

/**	joystick
	public static final Joystick driveController = new Joystick(0);
	public static final Joystick rotController = new Joystick(1); */

/**	xbox */
	public static final XboxController xboxController = new XboxController(0);

	// updatemanager

	private final UpdateManager updateManager = new UpdateManager(
		drivetrain
	);

	public RobotContainer() {
		CommandScheduler.getInstance().setDefaultCommand(drivetrain, new DriveCommand(
			drivetrain,
			() -> xboxController.getLeftX(),
			() -> xboxController.getLeftY(),
			() -> xboxController.getRightX(),
			() -> xboxController.getRightTriggerAxis()
		));

		updateManager.startLoop(5.0e-3);

		configureButtonBindings();
	}

	private void configureButtonBindings() {
		xboxController.getBackButton().whenPressed(
			() -> drivetrain.resetGyroAngle(Rotation2.ZERO)
		);
	}
}
