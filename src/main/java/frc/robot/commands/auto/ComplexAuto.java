package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.drive.DriveAimStop;
import frc.robot.commands.drive.DriveDistance;
import frc.robot.commands.indexer.ForwardIndexer;
import frc.robot.commands.shooter.ShortShooter;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.IndexerSubsystem;

/**
 * A complex auto command that starts the shooter Aims, Shoots Balls drives forward
 */
public class ComplexAuto extends SequentialCommandGroup {
  /**
   * Creates a new ComplexAuto.
   *
   * @param drive The drive subsystem this command will run on
   * @param indexer The indexer subsystem this command will run on
   * @param shooter The shooter subsystem this command will run on
   */
  public ComplexAuto(DriveSubsystem drive, IndexerSubsystem indexer, ShooterSubsystem shooter) {
    addCommands(
          new ParallelCommandGroup(
            new ShortShooter(shooter),
            new DriveAimStop(drive)
          ),
        new ForwardIndexer(indexer), // run indexer
        new WaitCommand(5.0),
        new DriveDistance(5, 0.4, drive)
      );
  }

}
