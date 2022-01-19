package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.math.Vector2;

import java.util.function.DoubleSupplier;

public class DriveCommand extends CommandBase {
    private final DrivetrainSubsystem drivetrain;
    private final double forward;
    private final double strafe;
    private final double rotation;

    public DriveCommand(DrivetrainSubsystem drivetrain,
                        double forward,
                        double strafe,
                        double rotation
			double speed) {
        this.drivetrain = drivetrain;
        this.forward = forward;
        this.strafe = -strafe;
        this.rotation = rotation;
	this.speed = speed;

        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
	if (speed > 0.0) {
	        drivetrain.drive(
        	        new Vector2(
                	        forward,
                	        strafe
                	),
                	rotation,
                	true
        	);
	} else {
	        drivetrain.drive(
        	        new Vector2(
                	        forward / 2,
                	        strafe / 2
                	),
                	rotation / 2,
                	true
        	);
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.drive(Vector2.ZERO, 0.0, false);
    }
}
