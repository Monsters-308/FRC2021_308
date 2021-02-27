package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.drive.DriveAimStop;
import frc.robot.commands.drive.DriveDistance;
import frc.robot.commands.indexer.ForwardIndexer;
import frc.robot.commands.shooter.ShootSpeed;
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
        new SequentialCommandGroup(
          new ParallelCommandGroup(
            new ShootSpeed(shooter, Constants.ShooterConstants.kShortShotRPM),
            new DriveAimStop(drive)
          ),
        new ForwardIndexer(indexer),
        new WaitCommand(Constants.AutoConstants.kAutoStraightWaitTime),
        new DriveDistance(
          Constants.AutoConstants.kAutoStraightDistance, 
          Constants.AutoConstants.kAutoStraightSpeed,
          true,
           drive)
        )
      );
  }

}
