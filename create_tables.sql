-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema mana_calculator
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mana_calculator
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mana_calculator` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `mana_calculator` ;

-- -----------------------------------------------------
-- Table `mana_calculator`.`affinity_conflicts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mana_calculator`.`affinity_conflicts` ;

CREATE TABLE IF NOT EXISTS `mana_calculator`.`affinity_conflicts` (
  `number_of_aspects` INT NOT NULL,
  `con_save_increase` INT NULL DEFAULT NULL,
  `benefit` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`number_of_aspects`),
  UNIQUE INDEX `number_of_aspects_UNIQUE` (`number_of_aspects` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `mana_calculator`.`affinity_conflicts` (number_of_aspects, con_save_increase, benefit)
VALUES
	(1, 0, 'None'),
	(2, 3, 'The duration of the spell doubles, or you can reroll a number of damage dice up to your proficiency bonus (your choice).'),
	(3, 6, 'The duration of the spell lasts until you finish a long rest, or you use the highest number possible for each damage die (your choice).');


-- -----------------------------------------------------
-- Table `mana_calculator`.`casters`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mana_calculator`.`casters` ;

CREATE TABLE IF NOT EXISTS `mana_calculator`.`casters` (
  `uuid` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `mana_points` INT NOT NULL DEFAULT '0',
  `mind` INT NOT NULL DEFAULT '0',
  `source` INT NOT NULL DEFAULT '0',
  `will` INT NOT NULL DEFAULT '0',
  PRIMARY KEY (`uuid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mana_calculator`.`codas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mana_calculator`.`codas` ;

CREATE TABLE IF NOT EXISTS `mana_calculator`.`codas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `aspect` VARCHAR(255) NULL DEFAULT NULL,
  `description` LONGTEXT NOT NULL,
  `cost` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `mana_calculator`.`codas` (name, aspect, description, cost)
VALUES
	('Alter Saving Throw', 'Will', 'Choose a primary or secondary effect and change which ability is used for saving throws made to resist it. The DM can increase the cost of this coda if there doesn''t seem to be a logical way to avoid or resist the spell using the chosen ability.', 3),
	('Autonomous', 'Mind', 'The crafted spell does not require you to maintain concentration for its duration.', 1),
	('Damage Increase', 'Any', 'Choose a damage dealing primary or secondary effect and increase its base damage. If you spend 1 Mana Point, you increase the base damage of the chosen effect by one die (from 2d6 to 3d6, for example). However, each increase also increases the DC of the Spellcrafting Constitution save by 1.', 1),
	('Echoed', 'Mind', 'Choose a primary or secondary effect. One round after your crafted spell is cast, the chosen effect triggers again, using the same target and base spell adjustments of the original crafted spell. If the duration of the effect was 1d4 rounds or longer, the echo lasts until the start of your next turn. Otherwise, its duration is instantaneous.', 1),
	('Alter Aspect', 'No Specific Aspect', 'Choose a primary effect, secondary effect, or coda, and change the affinity for that effect or coda to a different aspect.', 1),
	('Persistent', 'Any', 'A primary or secondary spell effect of your choice that does not deal damage has its duration extended to unlimited and no longer requires concentration to maintain. However, at the end of each long rest, you must succeed on a DC 20 Constitution saving throw to continue the effect.', 2),
	('Power Word', 'Any', 'A primary or secondary effect of your choice no longer allows its target to make a saving throw to avoid or prevent it from affecting them. If the effect has multiple targets, only one is automatically affected. You can pay an additional 2 Mana Points per additional target to affect other creatures targeted by the spell. If the effect has a duration longer than instantaneous, a target can roll a Wisdom saving throw at the end of each of its turns, ending the effect on a success.', 2),
	('Ricochet', 'Source', 'Choose a primary or secondary effect. If a target creature fails the saving throw against that effect, the effect ricochets to another creature of your choice within 30 feet of the first target. The new target must make the saving throw, and the effect continues to ricochet to additional targets, each within 30 feet of the previous target, until a target succeeds on its saving throw. Each creature can only be targeted by the effect once. You can only have one ricochet coda on a crafted spell.', 2),
	('Reactive Trigger', 'Any', 'All effects of this spell are held in stasis for the duration. The first time the target of the spell is targeted with either an attack or a spell, or effect that requires a saving throw, the target can use its reaction to activate this spell. All effects and codas immediately trigger as normal upon activation.', 1),
	('Stealthed', 'Will', 'Choose a primary or secondary effect. The effect becomes both silent and invisible. For example, a stealthed indirect energy effect will still deal damage, and the injuries and destruction it causes are still clearly visible. However, it makes no noise and creates no light.', 1);


-- -----------------------------------------------------
-- Table `mana_calculator`.`primary_effects`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mana_calculator`.`primary_effects` ;

CREATE TABLE IF NOT EXISTS `mana_calculator`.`primary_effects` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `aspect` VARCHAR(255) NULL DEFAULT NULL,
  `description` LONGTEXT NOT NULL,
  `cost` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `mana_calculator`.`primary_effects` (name, aspect, description, cost)
VALUES
	('Beguile the Mind', 'Mind', 'Each target creature within range must make a Wisdom saving throw or become charmed by you for the duration. A charmed creature is easily distracted, susceptible to your influence, or otherwise emotionally manipulatable. While charmed, and even after the spell ends, the target doesn''t know it was charmed by you. A charmed creature might believe you are a friendly acquaintance, not notice you or your companions, have its feelings about another creature changed, or suffer a similar effect of your choice. The DM determines how this affects the target''s behavior. If a charmed creature takes damage, it can repeat the saving throw, ending the effect on itself on a success.', 1),
	('Control the Mind', 'Mind', 'Each target creature within range must succeed on a Wisdom saving throw or you take over its mind, directly controlling it for the duration. On its turn, a controlled creature takes only the actions you choose and doesn''t do anything that you don''t allow it to do. The target is aware of your mind control. If a controlled creature takes damage, it can repeat the saving throw, ending the effect on itself on a success.', 3),
	('Direct Energy', 'Source', 'When you cast the spell, make a ranged spell attack against each target creature or object within range. On a hit, the spell deals acid, cold, fire, lightning, or thunder damage (your choice) equal to the effect''s base damage.', 1),
	('Indirect Energy', 'Source', 'When you cast the spell, each target creature or object within range must make a Dexterity saving throw. A target takes acid, cold, fire, lightning, or thunder damage (your choice) equal to the effect''s base damage on a failed save, or half as much damage on a successful one.', 1),
	('Boost of Curtail Capacity', 'Mind', 'You can increase or decrease one of the ability scores (your choice) of each target creature within range by 2 for the duration. All targets increase or decrease the same attribute. For every 1 additional Mana Point you spend, you can increase or decrease the attribute score by 1 additional point. An unwilling target can make a Constitution saving throw to resist the effect. At the end of each of its turns, a target can repeat the save, ending the effect on itself on a success.', 1),
	('Feral Transformation', 'Source', 'For the duration, each target creature within range gains one trait or feature of your choice from any Beast with a challenge rating equal to or less than the target''s challenge rating (or a target''s level if it doesn''t have a challenge rating). At the DM''s discretion, you can craft a Beast-like feature that doesn''t appear in any stat block. If you do so, the DM determines the exact results of the transformation. An unwilling target can make a Constitution saving throw to resist the effect. At the end of each of its turns, a target can repeat the save, ending the effect on itself on a success.', 1),
	('Temporary Feat', 'Will', 'Each target creature within range gains a feat of your choice for the duration. If you don''t possess the feat yourself, the cost of this effect is doubled.', 2),
	('Transposition', 'Will', 'Select a number of target creatures or objects of the same size within range (can include yourself). You magically swap the positions of the targets. Objects moved in this way must be unattended. An unwilling creature can make a Constitution saving throw to resist the effect.', 2),
	('Transmogrify Object', 'Will', 'Select a number of target nonmagical objects within range and change them into a different type of nonmagical object for the duration. The original object must be made of the same material as the one you wish it to become. For example, you can change a longsword into a dagger. A creature holding an object targeted by this effect can make a Wisdom saving throw to resist the change. The object is transformed back at the end of the spell''s duration, retaining any damage it may have suffered.', 1);


-- -----------------------------------------------------
-- Table `mana_calculator`.`effective_spell_level`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mana_calculator`.`effective_spell_level` ;

CREATE TABLE IF NOT EXISTS `mana_calculator`.`effective_spell_level` (
  `spell_level` INT NOT NULL,
  `character_level` VARCHAR(255) NULL DEFAULT NULL,
  `single_damage` INT NULL DEFAULT NULL,
  `multiple_damage` INT NULL DEFAULT NULL,
  PRIMARY KEY (`spell_level`),
  UNIQUE INDEX `spell_level_UNIQUE` (`spell_level` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `mana_calculator`.`effective_spell_level` (character_level, spell_level, single_damage, multiple_damage)
VALUES
	('1st-2nd', 1, 2, 2),
	('3rd-4th', 2, 3, 4),
	('5th-6th', 3, 5, 6),
	('7th-8th', 4, 6, 7),
	('9th-10th', 5, 8, 8),
	('11th-12th', 6, 10, 11),
	('13th-14th', 7, 11, 12),
	('15-16th', 8, 12, 13);


-- -----------------------------------------------------
-- Table `mana_calculator`.`custom_spells`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mana_calculator`.`custom_spells` ;

CREATE TABLE IF NOT EXISTS `mana_calculator`.`custom_spells` (
  `uuid` VARCHAR(255) NOT NULL,
  `origin_caster_id` VARCHAR(255) NULL DEFAULT NULL,
  `additional_caster_id_list` LONGTEXT NOT NULL,
  `spell_level_id` INT NOT NULL,
  `primary_effect_id` INT NOT NULL,
  `secondary_effect_id_list` VARCHAR(255) NOT NULL,
  `coda_id_list` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE INDEX `uuid_UNIQUE` (`uuid` ASC) VISIBLE,
  INDEX `spell_level_idx` (`spell_level_id` ASC) VISIBLE,
  INDEX `primary_effect_idx` (`primary_effect_id` ASC) VISIBLE,
  INDEX `origin_caster_idx` (`origin_caster_id` ASC) VISIBLE,
  CONSTRAINT `custom_spell_origin`
    FOREIGN KEY (`origin_caster_id`)
    REFERENCES `mana_calculator`.`casters` (`uuid`)
    ON DELETE SET NULL,
  CONSTRAINT `primary_effect`
    FOREIGN KEY (`primary_effect_id`)
    REFERENCES `mana_calculator`.`primary_effects` (`id`),
  CONSTRAINT `spell_level`
    FOREIGN KEY (`spell_level_id`)
    REFERENCES `mana_calculator`.`effective_spell_level` (`spell_level`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mana_calculator`.`custom_spell_base`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mana_calculator`.`custom_spell_base` ;

CREATE TABLE IF NOT EXISTS `mana_calculator`.`custom_spell_base` (
  `spell_id` VARCHAR(255) NOT NULL,
  `cost` INT NULL DEFAULT NULL,
  `casting_time` VARCHAR(255) NULL DEFAULT NULL,
  `range` VARCHAR(255) NULL DEFAULT NULL,
  `target` INT NULL DEFAULT NULL,
  `area` VARCHAR(255) NULL DEFAULT NULL,
  `area_size` VARCHAR(255) NULL DEFAULT NULL,
  `duration` VARCHAR(255) NULL DEFAULT NULL,
  `casting_range` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`spell_id`),
  UNIQUE INDEX `spell_id_UNIQUE` (`spell_id` ASC) VISIBLE,
  CONSTRAINT `spell`
    FOREIGN KEY (`spell_id`)
    REFERENCES `mana_calculator`.`custom_spells` (`uuid`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mana_calculator`.`custom_spell_effect_customizations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mana_calculator`.`custom_spell_effect_customizations` ;

CREATE TABLE IF NOT EXISTS `mana_calculator`.`custom_spell_effect_customizations` (
  `spell_id` VARCHAR(255) NOT NULL,
  `effect_name` VARCHAR(255) NOT NULL,
  `effect_data` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`spell_id`, `effect_name`),
  CONSTRAINT `spell_for_effect_customizations`
    FOREIGN KEY (`spell_id`)
    REFERENCES `mana_calculator`.`custom_spells` (`uuid`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mana_calculator`.`secondary_effects`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mana_calculator`.`secondary_effects` ;

CREATE TABLE IF NOT EXISTS `mana_calculator`.`secondary_effects` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `aspect` VARCHAR(255) NULL DEFAULT NULL,
  `description` LONGTEXT NOT NULL,
  `cost` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `mana_calculator`.`secondary_effects` (name, aspect, description, cost)
VALUES
	('Hobble', 'Will', 'Each target of the spell must also succeed on a Constitution saving throw or its speed is halved for the duration.', 1),
	('Shockwave', 'Source', 'Choose one creature targeted by the primary effect. Each creature within 5 feet of the chosen creature must succeed on a Dexterity saving throw or take acid, cold, fire, lightning, or thunder damage (your choice) equal to half of the effect''s base damage.', 1),
	('Assist or Hinder', 'Mind', 'Choose an ability. A creature targeted by the primary effect of the spell has advantage or disadvantage (your choice) on ability checks using that ability until the start of your next turn. An unwilling creature can make a Wisdom saving throw to resist this effect.', 1),
	('Elemental Flare', 'Source', 'Choose a source of acid, fire, ice, or lightning within 10 feet of one target of the primary effect. The target must succeed on a Dexterity saving throw or immediately take damage, of a type appropriate to that element, equal to half of the effect''s base damage. The source must be either something that is generating that element, such as a torch, or a pool of the element, such as a puddle of acid, at least 5 feet long or wide.', 1),
	('Expansion or Reduction', 'Source', 'A creature of your choice targeted by the primary effect of the spell increases or decreases its size (your choice) by one category for the duration. An unwilling creature can make a Wisdom saving throw to resist the effect. The target gains advantage or disadvantage on Strength checks, its weapon attacks deal +1d4 or -1d4 damage (depending on whether it was made larger or smaller), and its weight changes accordingly.', 1),
	('Inhibit', 'Will', 'Choose a creature within 30 feet of one target of the primary effect. That target must succeed on a Constitution saving throw or have disadvantage on a saving throw of your choice for the duration. The target can repeat the saving throw at the end of each of its turns, ending the effect on itself on a success.', 1),
	('Repulsion', 'Will', 'Choose one target of the primary effect within range. The target is surrounded by a translucent barrier in a 10-foot radius that moves with them for the duration. The barrier prevents creatures other than Constructs and Undead from passing through. Creatures can cast spells or attack with ranged or reach weapons through the barrier.', 2),
	('Shape Repression', 'Mind', 'Choose one creature targeted by the primary effect. The creature must succeed on a Wisdom saving throw or be forced to return to its natural state until the end of its next turn.', 1);


-- -----------------------------------------------------
-- Table `mana_calculator`.`spell_bases`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mana_calculator`.`spell_bases` ;

CREATE TABLE IF NOT EXISTS `mana_calculator`.`spell_bases` (
  `cost` INT UNSIGNED NOT NULL,
  `casting_time` VARCHAR(255) NULL DEFAULT NULL,
  `casting_range` VARCHAR(255) NULL DEFAULT NULL,
  `target` INT NULL DEFAULT NULL,
  `area` VARCHAR(255) NULL DEFAULT NULL,
  `area_size` VARCHAR(255) NULL DEFAULT NULL,
  `duration` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`cost`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `mana_calculator`.`spell_bases` (cost, casting_time, casting_range, target, area, area_size, duration)
VALUES
	(0, 'Action', '15 ft. or self', 1, 'Cone, Line, Sphere, or one 5ft. cube', '15 ft.', 'Instantaneous, or 1d4 rounds'),
	(1, 'Bonus action', '30 ft.', 2, 'Cylinder or two 5ft. cubes', '20 ft.', '1 Minute'),
	(2, 'Reaction', '60 ft.', 3, 'Four 5ft. cubes', '30 ft.', '10 Minutes'),
	(3, '', '90 ft.', 4, 'Four nonadjacent 5ft. cubes', '60 ft.', '1 Hour'),
	(4, '', '120 ft.', 5, 'Six nonadjacent 5ft. cubes', '', '8 Hours'),
	(5, '', '1 Mile', 6, '', '90 ft.', '24 Hours'),
	(6, '', '10 Miles', 7, '', '', '');


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
