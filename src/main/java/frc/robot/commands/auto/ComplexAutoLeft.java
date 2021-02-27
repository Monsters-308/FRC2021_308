package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.drive.DriveAimStopLeft;
import frc.robot.commands.drive.DriveDistance;
import frc.robot.commands.indexer.ForwardIndexer;
import frc.robot.commands.shooter.ShootSpeed;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.IndexerSubsystem;

/**
 * A complex auto command that starts the shooter Aims, Shoots Balls drives forward
 */
public class ComplexAutoLeft extends SequentialCommandGroup {
  /**
   * Creates a new ComplexAuto.
   *
   * @param drive   The drive subsystem this command will run on
   * @param indexer The indexer subsystem this command will run on
   * @param shooter The shooter subsystem this command will run on
   */
  public ComplexAutoLeft(DriveSubsystem drive, IndexerSubsystem indexer, ShooterSubsystem shooter) {
    addCommands(
        new SequentialCommandGroup(
          new ParallelCommandGroup(
            new ShootSpeed(shooter, ShooterConstants.kShortShotRPM),
            new DriveDistance(AutoConstants.kAutoStraightDistance, AutoConstants.kAutoStraightSpeed, true, drive),
            new DriveAimStopLeft(drive)
          ),
        new ForwardIndexer(indexer)
        )
      );
  }

}