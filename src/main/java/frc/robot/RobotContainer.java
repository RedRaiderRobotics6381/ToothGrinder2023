package frc.robot;

import java.util.ArrayList;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.Arm.ArmCloseGrabberCmd;
import frc.robot.commands.Arm.ArmOpenGrabberCmd;
import frc.robot.commands.Arm.ArmSliderBottomCmd;
import frc.robot.commands.Arm.ArmSliderHumanPlayerCmd;
import frc.robot.commands.Arm.ArmSliderTopCmd;
import frc.robot.commands.Arm.ArmSuckCmd;
//import frc.robot.commands.Auto.AutoWaitCmd;
import frc.robot.commands.Drive.SwerveJoystickCmd;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.SwerveSubsystem;

public class RobotContainer {

        private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();
        private final ArmSubsystem armSubsystem = new ArmSubsystem();

        public final XboxController driverJoytick = new XboxController(OIConstants.kDriverControllerPort);
        public final static XboxController secondaryJoystick = new XboxController(
                        OIConstants.kSecondaryDriverControllerPort);

        public RobotContainer() {
                swerveSubsystem.setDefaultCommand(new SwerveJoystickCmd(
                                swerveSubsystem,
                                () -> -driverJoytick.getRawAxis(OIConstants.kDriverYAxis),
                                () -> -driverJoytick.getRawAxis(OIConstants.kDriverXAxis),
                                () -> -driverJoytick.getRawAxis(OIConstants.kDriverRotAxis),
                                () -> driverJoytick.getRawAxis(2),
                                () -> driverJoytick.getRawButton(5),
                                () -> driverJoytick.getRawButton(6)));

                configureButtonBindings();
        }
// Noah == fart 
        private void configureButtonBindings() {
                new JoystickButton(secondaryJoystick, 1).onTrue(
                        new ArmSliderBottomCmd(armSubsystem));
                new JoystickButton(secondaryJoystick, 2).onTrue(
                        new ArmSliderHumanPlayerCmd(armSubsystem));
                new JoystickButton(secondaryJoystick, 4).onTrue(
                        new ArmSliderTopCmd(armSubsystem));

                new JoystickButton(secondaryJoystick, 5).onTrue(
                                new ArmOpenGrabberCmd(armSubsystem, () -> secondaryJoystick.getRawButton(5)));
                new JoystickButton(secondaryJoystick, 6).onTrue(
                                new ArmCloseGrabberCmd(armSubsystem, () -> secondaryJoystick.getRawButton(6)));

                new JoystickButton(secondaryJoystick, 3).toggleOnTrue(
                        new ArmSuckCmd(armSubsystem));
        }

        public Command getAutonomousCommand() {
                // 1. Create trajectory settings
                TrajectoryConfig config = new TrajectoryConfig(Units.feetToMeters(12), Units.feetToMeters(12));
                config.setReversed(true);

                // 2. Generate trajectory
                var start = new Pose2d(0, 0,
                                Rotation2d.fromDegrees(-180));
                var end = new Pose2d(6.666, 0,
                                Rotation2d.fromDegrees(-160));

                var interior = new ArrayList<Translation2d>();
                // interior.add(new Translation2d(Units.feetToMeters(14.54),
                // Units.feetToMeters(23.23)));
                // interior.add(new Translation2d(Units.feetToMeters(21.04),
                // Units.feetToMeters(18.23)));

                var trajectory = TrajectoryGenerator.generateTrajectory(
                                start,
                                interior,
                                end,
                                config);

                // 3. Define PID controllers for tracking trajectory
                PIDController xController = new PIDController(AutoConstants.kPXController, 0, 0);
                PIDController yController = new PIDController(AutoConstants.kPYController, 0, 0);
                ProfiledPIDController thetaController = new ProfiledPIDController(
                                AutoConstants.kPThetaController, 0, 0, AutoConstants.kThetaControllerConstraints);
                thetaController.enableContinuousInput(-Math.PI, Math.PI);

                // 4. Construct command to follow trajectory
                // SwerveControllerCommand swerveControllerCommand = new SwerveControllerCommand(
                //                 trajectory,
                //                 swerveSubsystem::getPose,
                //                 DriveConstants.kDriveKinematics,
                //                 xController,
                //                 yController,
                //                 thetaController,
                //                 swerveSubsystem::setModuleStates,
                //                 swerveSubsystem);

                // 1B. Create trajectory settings
                TrajectoryConfig configB = new TrajectoryConfig(Constants.DriveConstants.kPhysicalMaxSpeedMetersPerSecond, Constants.DriveConstants.kTeleDriveMaxAccelerationUnitsPerSecond);
                config.setReversed(true);

                // 2. Generate trajectory
                var startB = new Pose2d(0, 0,
                                Rotation2d.fromDegrees(-180));
                var endB = new Pose2d(6.666, 0,
                                Rotation2d.fromDegrees(-160));

                var interiorB = new ArrayList<Translation2d>();
                // interior.add(new Translation2d(Units.feetToMeters(14.54),
                // Units.feetToMeters(23.23)));
                // interior.add(new Translation2d(Units.feetToMeters(21.04),
                // Units.feetToMeters(18.23)));

                var trajectoryB = TrajectoryGenerator.generateTrajectory(
                                startB,
                                interiorB,
                                endB,
                                configB);

                // 4. Construct command to follow trajectory
                SwerveControllerCommand swerveControllerCommandB = new SwerveControllerCommand(
                                trajectoryB,
                                swerveSubsystem::getPose,
                                DriveConstants.kDriveKinematics,
                                xController,
                                yController,
                                thetaController,
                                swerveSubsystem::setModuleStates,
                                swerveSubsystem);

                // 5. Add some init and wrap-up, and return everything
                // return new SequentialCommandGroup(
                // new InstantCommand(() ->
                // swerveSubsystem.resetOdometry(trajectory.getInitialPose())),
                // swerveControllerCommand,
                // new InstantCommand(() -> swerveSubsystem.stopModules()));

                return new SequentialCommandGroup(
                                new InstantCommand(() -> swerveSubsystem.resetOdometry(trajectory.getInitialPose())),
                                // new AutoManipulatorCmd(armSubsystem, -20.0),
                                // new ArmSliderTopCmd(armSubsystem),
                                // new AutoOpenGrabberCmd(armSubsystem, 15.0),
                                // new AutoWaitCmd(armSubsystem, 1000),
                                // new ArmSliderBottomCmd(armSubsystem),
                                swerveControllerCommandB,
                                new InstantCommand(() -> swerveSubsystem.stopModules()));
        }
}