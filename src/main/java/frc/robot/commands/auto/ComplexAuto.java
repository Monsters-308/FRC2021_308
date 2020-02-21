package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.Constants.AutoConstants;
import frc.robot.commands.drive.DriveTime;
import frc.robot.commands.indexer.ForwardIndexer;
import frc.robot.commands.shooter.ShortShooter;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.IndexerSubsystem;

/**
 * A complex auto command that drives forward, extend intake, run intake forward and then drives backward.
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
        // Drive forward the specified distance
        new DriveTime(AutoConstants.kAutoDriveTime, AutoConstants.kAutoDriveSpeed,
                          drive),

        new ShortShooter(shooter), // get up to speed

        new ForwardIndexer(indexer) // run indexer
                          );
  }

}
