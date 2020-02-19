package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.auto.ComplexAuto;
import frc.robot.commands.drive.DefaultDrive;
import frc.robot.commands.drive.DriveDistance;
import frc.robot.commands.intake.ExtendIntake;
import frc.robot.commands.intake.ForwardIntake;
import frc.robot.commands.drive.HalveDriveSpeed;
import frc.robot.commands.hopper.ForwardHopper;
import frc.robot.commands.hopper.PulseHopper;
import frc.robot.commands.hopper.StopHopper;
import frc.robot.commands.indexer.ForwardIndexer;
import frc.robot.commands.indexer.StopIndexer;
import frc.robot.commands.intake.RetractIntake;
import frc.robot.commands.intake.StopIntake;
import frc.robot.commands.lift.ForwardLift;
import frc.robot.commands.lift.ReverseLift;
import frc.robot.commands.lift.StopLift;
import frc.robot.commands.shooter.ForwardShooter;
import frc.robot.commands.shooter.StopShooter;
import frc.robot.commands.wof.ForwardWOF;
import frc.robot.commands.wof.StopWOF;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.WOFSubsystem;
import frc.robot.subsystems.IndexerSubsystem;

import static edu.wpi.first.wpilibj.XboxController.Button;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final HopperSubsystem m_hopperSubsystem = new HopperSubsystem();
  private final IndexerSubsystem m_indexerSubsystem = new IndexerSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final WOFSubsystem m_wofSubsystem = new WOFSubsystem();
  private final LiftSubsystem m_liftSubsystem = new LiftSubsystem();
  // The autonomous routines

  // A simple auto routine that drives forward a specified distance, and then stops.
  private final Command m_simpleAuto =
      new DriveDistance(AutoConstants.kAutoDriveDistanceInches, AutoConstants.kAutoDriveSpeed,
                        m_robotDrive);

  // A complex auto routine that drives forward, extends intake, and then drives backward.
  private final Command m_complexAuto = new ComplexAuto(m_robotDrive, m_intakeSubsystem, m_hopperSubsystem, m_indexerSubsystem, m_shooterSubsystem);

  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  // The driver's controller
  XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
  // The codriver's controller
  XboxController m_coDriverController = new XboxController(OIConstants.kCoDriverControllerPort);


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
    // Set the default drive command to split-stick arcade drive
    m_robotDrive.setDefaultCommand(
        // A split-stick arcade command, with forward/backward controlled by the left
        // hand, and turning controlled by the right.
        new DefaultDrive(
            m_robotDrive,
            () -> m_driverController.getY(GenericHID.Hand.kLeft),
            () -> m_driverController.getX(GenericHID.Hand.kRight)));

    // Add commands to the autonomous command chooser
    m_chooser.addOption("Simple Auto", m_simpleAuto);
    m_chooser.addOption("Complex Auto", m_complexAuto);

    // Put the chooser on the dashboard
    Shuffleboard.getTab("Autonomous").add(m_chooser);
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // While holding the 'A' button, power Shooter
    new JoystickButton(m_driverController, Button.kA.value)
        .whenPressed(new ForwardShooter(m_shooterSubsystem))
        .whenReleased(new StopShooter(m_shooterSubsystem));
    // While holding the 'Right Bumper' button, power indexer
    new JoystickButton(m_driverController, Button.kBumperRight.value)
        .whenPressed(new ForwardIndexer(m_indexerSubsystem))
        .whenReleased(new StopIndexer(m_indexerSubsystem));
    // While holding the 'B' button, power hopper
    new JoystickButton(m_driverController, Button.kB.value)
        .whenPressed(new ForwardHopper(m_hopperSubsystem))
        .whenReleased(new StopHopper(m_hopperSubsystem));
    // While holding the 'X' button, power Intake
    new JoystickButton(m_driverController, Button.kX.value)
        .whenPressed(new ForwardIntake(m_intakeSubsystem))
        .whenReleased(new StopIntake(m_intakeSubsystem));
    // While holding the 'Y' button, power WOF
    new JoystickButton(m_driverController, Button.kY.value)
      .whenPressed(new ForwardWOF(m_wofSubsystem))
      .whenReleased(new StopWOF(m_wofSubsystem));
    // While holding 'Right Bumper' button, power lift
    new JoystickButton(m_coDriverController, Button.kB.value)
      .whenPressed(new ForwardLift(m_liftSubsystem))
      .whenReleased(new StopLift(m_liftSubsystem));

    new JoystickButton(m_coDriverController, Button.kX.value)
      .whenPressed(new ReverseLift(m_liftSubsystem))
      .whenReleased(new StopLift(m_liftSubsystem));

    new JoystickButton(m_coDriverController, Button.kY.value)
      .whenPressed(new PulseHopper(m_hopperSubsystem))
      .whenReleased(new StopHopper(m_hopperSubsystem)); 
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }
}
