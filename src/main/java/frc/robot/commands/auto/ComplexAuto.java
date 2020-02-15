package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.Constants.AutoConstants;
import frc.robot.commands.drive.DriveDistance;
import frc.robot.commands.hopper.ForwardHopper;
import frc.robot.commands.indexer.ForwardIndexer;
import frc.robot.commands.intake.ExtendIntake;
import frc.robot.commands.intake.ForwardIntake;
import frc.robot.commands.shooter.ForwardShooter;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
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
   * @param intake The intake subsystem this command will run on
   * @param hopper The hopper subsystem this command will run on
   * @param indexer The indexer subsystem this command will run on
   * @param shooter The shooter subsystem this command will run on
   */
  public ComplexAuto(DriveSubsystem drive, IntakeSubsystem intake, HopperSubsystem hopper, IndexerSubsystem indexer, ShooterSubsystem shooter) {
    addCommands(
        // Drive forward the specified distance
        new DriveDistance(AutoConstants.kAutoDriveDistanceInches, AutoConstants.kAutoDriveSpeed,
                          drive),

        new ForwardShooter(shooter),

        new ExtendIntake(intake),

        new ForwardIntake(intake),

        new ForwardHopper(hopper),

        new ForwardIndexer(indexer),

        // Drive backward the specified distance
        new DriveDistance(AutoConstants.kAutoBackupDistanceInches, -AutoConstants.kAutoDriveSpeed,
                          drive)
                          
                          
                          );
  }

}
