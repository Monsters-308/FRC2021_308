package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.drive.DriveAim;
import frc.robot.commands.drive.DriveAimStop;
import frc.robot.commands.drive.DriveDistance;
import frc.robot.commands.hopper.ForwardHopper;
import frc.robot.commands.indexer.ForwardIndexer;
import frc.robot.commands.intake.ForwardIntake;
import frc.robot.commands.shooter.ShortShooter;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

/**
 * A complex auto command that starts the shooter drives forward, Backward, Forward intakes 2 balls Aims and shoots .
 */
public class AutoNearTrench extends SequentialCommandGroup {
  /**
   * Creates a new AutoNearTrench.
   *
   * @param drive The drive subsystem this command will run on
   * @param indexer The indexer subsystem this command will run on
   * @param shooter The shooter subsystem this command will run on
   * @param intake The intake subsystem this command will run on
   * @param hopper The hopper subsystem this command will run on
   */
  public AutoNearTrench(DriveSubsystem drive, IndexerSubsystem indexer, ShooterSubsystem shooter,IntakeSubsystem intake, HopperSubsystem hopper) {
    addCommands(
      new SequentialCommandGroup(
        new ParallelCommandGroup(
          new ShortShooter(shooter),
          new SequentialCommandGroup(
            new WaitCommand(1),
            new DriveDistance(3,0.6,drive),
            new DriveDistance(-3,-0.6,drive),
            new ForwardIntake(intake),
            new ForwardHopper(hopper),
            new DriveDistance(60,0.5,drive),
            new DriveAimStop(drive))
            ),
        new ForwardIndexer(indexer) // run indexer
      )

      );
  }

}
