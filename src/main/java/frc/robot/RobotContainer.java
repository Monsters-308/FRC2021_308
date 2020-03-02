package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.auto.AutoFarTrench;
import frc.robot.commands.auto.AutoNearTrench;
import frc.robot.commands.auto.ComplexAuto;
import frc.robot.commands.drive.DefaultDrive;
import frc.robot.commands.drive.DriveAim;
import frc.robot.commands.drive.DriveDistance;
import frc.robot.commands.intake.ForwardIntake;
import frc.robot.commands.intake.PerpetualIntake;
import frc.robot.commands.hopper.ForwardHopper;
import frc.robot.commands.hopper.PerpetualHopper;
import frc.robot.commands.hopper.ReverseHopper;
import frc.robot.commands.hopper.StopHopper;
import frc.robot.commands.indexer.AutoIndexer;
import frc.robot.commands.indexer.ForwardIndexer;
import frc.robot.commands.indexer.ReverseIndexer;
import frc.robot.commands.indexer.StopIndexer;
import frc.robot.commands.intake.ReverseIntake;
import frc.robot.commands.intake.StopIntake;
import frc.robot.commands.led.LEDAllBlue;
import frc.robot.commands.led.LEDAllGreen;
import frc.robot.commands.led.LEDAllRed;
import frc.robot.commands.led.LEDFrenzy;
import frc.robot.commands.led.LEDGreenStreak;
import frc.robot.commands.led.LEDRedStreak;
import frc.robot.commands.lift.ForwardLift;
import frc.robot.commands.lift.ReverseLift;
import frc.robot.commands.lift.StopLift;
import frc.robot.commands.shooter.LongShooter;
import frc.robot.commands.shooter.ShortShooter;
import frc.robot.commands.shooter.StopShooter;
import frc.robot.commands.traverse.ForwardTraverse;
import frc.robot.commands.traverse.ReverseTraverse;
import frc.robot.commands.traverse.StopTraverse;
import frc.robot.commands.wof.SlowForwardWOF;
import frc.robot.commands.wof.SlowReverseWOF;
import frc.robot.commands.wof.StopWOF;
import frc.robot.commands.wof.WOFSpinSpecific;
import frc.robot.commands.wof.WOFSpinThree;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TraverseSubsystem;
import frc.robot.subsystems.WOFSubsystem;
import frc.robot.util.JoystickPOVButton;
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
  private final TraverseSubsystem m_traverseSubsystem = new TraverseSubsystem();
  private final LEDSubsystem m_ledSubsystem = new LEDSubsystem();
  // The autonomous routines

  // A simple auto routine that drives forward a specified distance, and then stops.
  private final Command m_simpleAuto =
      new DriveDistance(10,0.3,m_robotDrive);

  // A complex auto routine that drives forward, extends intake, and then drives backward.
  private final Command m_complexAuto = new ComplexAuto(m_robotDrive, m_indexerSubsystem, m_shooterSubsystem);
  private final Command m_autoNearTrench = new AutoNearTrench(m_robotDrive, m_indexerSubsystem, m_shooterSubsystem,m_intakeSubsystem,m_hopperSubsystem);
  private final Command m_autoFarTrench = new AutoFarTrench(m_robotDrive, m_indexerSubsystem, m_shooterSubsystem,m_intakeSubsystem,m_hopperSubsystem);

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

    // m_shooterSubsystem.setDefaultCommand(new ShortShooter(m_shooterSubsystem));
    // Add commands to the autonomous command chooser
    m_chooser.addOption("Move Off Line", m_simpleAuto);
    m_chooser.addOption("Shoot Straight", m_complexAuto);
    m_chooser.addOption("Near Trench", m_autoNearTrench);
    m_chooser.addOption("Far Trench", m_autoFarTrench);

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

    /**************************
     * Driver Button Commands *
    ***************************/
    // While holding the 'RightBumper' button, power Shooter Indexer and Hopper sequential util speed limit then index balls
    new JoystickButton(m_driverController, Button.kBumperRight.value)
        .whenPressed(
          new SequentialCommandGroup(
            new LongShooter(m_shooterSubsystem), // speed up to 100 RPM until running the indexer
            new ParallelCommandGroup(
              new ForwardIndexer(m_indexerSubsystem),
              new ForwardHopper(m_hopperSubsystem),
              new ForwardIntake(m_intakeSubsystem),
              new LEDFrenzy(m_ledSubsystem)
              )
            )
          )
        .whenReleased(
          new ParallelCommandGroup(
            new LongShooter(m_shooterSubsystem), 
            new StopHopper(m_hopperSubsystem),
            new StopIndexer(m_indexerSubsystem),
            new StopIntake(m_intakeSubsystem),
            new LEDAllRed(m_ledSubsystem)
          )
        );

    new JoystickButton(m_driverController, Button.kBumperLeft.value)
        .whenPressed(
          new SequentialCommandGroup(
            new ShortShooter(m_shooterSubsystem), // speed up to 100 RPM until running the indexer
            new ParallelCommandGroup(
              new ForwardIndexer(m_indexerSubsystem),
              new ForwardHopper(m_hopperSubsystem),
              new ForwardIntake(m_intakeSubsystem),
              new LEDFrenzy(m_ledSubsystem)
              )
            )
          )
        .whenReleased(
          new ParallelCommandGroup(
            new LongShooter(m_shooterSubsystem), 
            new StopHopper(m_hopperSubsystem),
            new StopIndexer(m_indexerSubsystem),
            new StopIntake(m_intakeSubsystem),
            new LEDAllRed(m_ledSubsystem)
          )
        );

    new JoystickPOVButton(m_driverController, JoystickPOVButton.NORTH)
        .whenPressed(new ForwardLift(m_liftSubsystem))
        .whenReleased(new StopLift(m_liftSubsystem));

    new JoystickPOVButton(m_driverController, JoystickPOVButton.SOUTH)
        .whenPressed(new ReverseLift(m_liftSubsystem))
        .whenReleased(new StopLift(m_liftSubsystem));

    new JoystickPOVButton(m_driverController, JoystickPOVButton.WEST)
        .whenPressed(new ForwardTraverse(m_traverseSubsystem))
        .whenReleased(new StopTraverse(m_traverseSubsystem));

    new JoystickPOVButton(m_driverController, JoystickPOVButton.EAST)
        .whenPressed(new ReverseTraverse(m_traverseSubsystem))
        .whenReleased(new StopTraverse(m_traverseSubsystem));


    //TODO Below for Auto AIM
    // While holding the 'X' button, For Future use for vision Aim system
    new JoystickButton(m_driverController, Button.kX.value)
        .whenPressed(
          new SequentialCommandGroup(
            new DriveAim(m_robotDrive, () -> m_driverController.getY(GenericHID.Hand.kLeft)),
            new LEDAllGreen(m_ledSubsystem)))
        .whenReleased(
          new DefaultDrive(m_robotDrive, () -> m_driverController.getY(GenericHID.Hand.kLeft),() -> m_driverController.getX(GenericHID.Hand.kRight)));

    new JoystickButton(m_driverController, Button.kY.value)
        .whenPressed(new StopShooter(m_shooterSubsystem));
  
  
    /*****************************
     * Co Driver Button Commands *
    ******************************/
    // While holding the 'A' button, power hopper, indexer and Intake using auto indexer option   
    new JoystickButton(m_coDriverController, Button.kA.value)
        .whenPressed(
          new SequentialCommandGroup(
            new LEDRedStreak(m_ledSubsystem),
            new ParallelRaceGroup(
              new AutoIndexer(m_indexerSubsystem,true,false),
              new PerpetualIntake(m_intakeSubsystem),
              new PerpetualHopper(m_hopperSubsystem)
            ),
            new ParallelCommandGroup(
              new StopIndexer(m_indexerSubsystem),
              new StopIntake(m_intakeSubsystem),
              new StopHopper(m_hopperSubsystem),
              new LEDAllGreen(m_ledSubsystem)
            )
          )
        )
        .whenReleased(
          new ParallelCommandGroup(
            new StopHopper(m_hopperSubsystem),
            new StopIntake(m_intakeSubsystem),
            new StopIndexer(m_indexerSubsystem)
          )
        );

    // While holding the 'B' button, power hopper and Intake only
    new JoystickButton(m_coDriverController, Button.kB.value)
        .whenPressed(
          new ParallelCommandGroup(
            new ForwardHopper(m_hopperSubsystem), 
            new ForwardIntake(m_intakeSubsystem),
            new LEDAllBlue(m_ledSubsystem)))
        .whenReleased(new ParallelCommandGroup(new StopHopper(m_hopperSubsystem), new StopIntake(m_intakeSubsystem)));

    // While holding the 'Y' button, power hopper and Intake only
    new JoystickButton(m_coDriverController, Button.kY.value)
        .whenPressed(new ParallelCommandGroup( new ReverseHopper(m_hopperSubsystem), new ReverseIntake(m_intakeSubsystem)))
        .whenReleased(new ParallelCommandGroup(new StopHopper(m_hopperSubsystem), new StopIntake(m_intakeSubsystem)));

    // While holding the 'LB' button, power hopper and Intake only
    new JoystickButton(m_coDriverController, Button.kBumperLeft.value)
        .whenPressed(new ParallelCommandGroup( 
          new ForwardHopper(m_hopperSubsystem), 
          new ForwardIntake(m_intakeSubsystem),
          new ForwardIndexer(m_indexerSubsystem),
          new LEDGreenStreak(m_ledSubsystem)))
        .whenReleased(new ParallelCommandGroup(new StopHopper(m_hopperSubsystem), new StopIntake(m_intakeSubsystem),new StopIndexer(m_indexerSubsystem)));

    // While holding the 'RB' button, power hopper and Intake only
    new JoystickButton(m_coDriverController, Button.kBumperRight.value)
        .whenPressed(new ParallelCommandGroup( new ReverseHopper(m_hopperSubsystem), new ReverseIntake(m_intakeSubsystem),new ReverseIndexer(m_indexerSubsystem),new LEDRedStreak(m_ledSubsystem)))
        .whenReleased(new ParallelCommandGroup(new StopHopper(m_hopperSubsystem), new StopIntake(m_intakeSubsystem), new StopIndexer(m_indexerSubsystem)));


    // TODO Finish Testing the below features
    new JoystickPOVButton(m_coDriverController, JoystickPOVButton.NORTH)
        .whenPressed(new WOFSpinThree(m_wofSubsystem))
        .whenReleased(new StopWOF(m_wofSubsystem));

    new JoystickPOVButton(m_coDriverController, JoystickPOVButton.SOUTH)
        .whenPressed(new WOFSpinSpecific(m_wofSubsystem))
        .whenReleased(new StopWOF(m_wofSubsystem));

    new JoystickPOVButton(m_coDriverController, JoystickPOVButton.WEST)
        .whenPressed(new SlowForwardWOF(m_wofSubsystem))
        .whenReleased(new StopWOF(m_wofSubsystem));

    new JoystickPOVButton(m_coDriverController, JoystickPOVButton.EAST)
        .whenPressed(new SlowReverseWOF(m_wofSubsystem))
        .whenReleased(new StopWOF(m_wofSubsystem));

  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }

  public XboxController getDriverController(){
    return m_driverController;
  }

  public XboxController getCoDriverController(){
    return m_coDriverController;
  }
  private static RobotContainer instance;
  public static RobotContainer getInstance(){
    if(instance == null){
      instance = new RobotContainer();

    }
    return instance;
  }
}
