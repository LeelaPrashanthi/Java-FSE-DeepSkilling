-- Create Department table
CREATE TABLE IF NOT EXISTS `ormlearn`.`department` (
  `dp_id` INT NOT NULL AUTO_INCREMENT,
  `dp_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`dp_id`)
);

-- Create Skill table
CREATE TABLE IF NOT EXISTS `ormlearn`.`skill` (
  `sk_id` INT NOT NULL AUTO_INCREMENT,
  `sk_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`sk_id`)
);

-- Create Employee table
CREATE TABLE IF NOT EXISTS `ormlearn`.`employee` (
  `em_id` INT NOT NULL AUTO_INCREMENT,
  `em_name` VARCHAR(45) NOT NULL,
  `em_salary` DOUBLE NOT NULL,
  `em_permanent` TINYINT NOT NULL,
  `em_date_of_birth` DATE NOT NULL,
  `em_department_id` INT,
  PRIMARY KEY (`em_id`),
  FOREIGN KEY (`em_department_id`) REFERENCES `department`(`dp_id`)
);

-- Create Employee_Skill mapping table for many-to-many relationship
CREATE TABLE IF NOT EXISTS `ormlearn`.`employee_skill` (
  `es_id` INT NOT NULL AUTO_INCREMENT,
  `es_em_id` INT NOT NULL,
  `es_sk_id` INT NOT NULL,
  PRIMARY KEY (`es_id`),
  FOREIGN KEY (`es_em_id`) REFERENCES `employee`(`em_id`),
  FOREIGN KEY (`es_sk_id`) REFERENCES `skill`(`sk_id`)
);

-- Insert sample Department data
INSERT INTO `department` (`dp_name`) VALUES 
('Engineering'),
('Marketing'),
('Finance'),
('Human Resources'),
('Sales'),
('Operations');

-- Insert sample Skill data
INSERT INTO `skill` (`sk_name`) VALUES 
('Java'),
('Python'),
('JavaScript'),
('React'),
('Angular'),
('Spring Boot'),
('Hibernate'),
('MySQL'),
('MongoDB'),
('AWS'),
('Docker'),
('Kubernetes'),
('Agile'),
('Scrum'),
('Project Management'),
('Data Analysis'),
('Machine Learning'),
('UI/UX Design'),
('Digital Marketing'),
('Sales Strategy');

-- Insert sample Employee data
INSERT INTO `employee` (`em_name`, `em_salary`, `em_permanent`, `em_date_of_birth`, `em_department_id`) VALUES 
('John Smith', 75000.00, 1, '1990-05-15', 1),
('Sarah Johnson', 65000.00, 1, '1988-12-03', 2),
('Michael Brown', 80000.00, 1, '1985-08-22', 1),
('Emily Davis', 70000.00, 0, '1992-03-10', 3),
('David Wilson', 90000.00, 1, '1983-11-18', 1),
('Lisa Anderson', 60000.00, 1, '1991-07-25', 4),
('Robert Taylor', 85000.00, 1, '1987-04-12', 5),
('Jennifer Martinez', 72000.00, 0, '1989-09-30', 2),
('Christopher Garcia', 78000.00, 1, '1986-01-08', 1),
('Amanda Rodriguez', 68000.00, 1, '1993-06-20', 6);

-- Insert sample Employee_Skill mapping data
INSERT INTO `employee_skill` (`es_em_id`, `es_sk_id`) VALUES 
(1, 1), (1, 6), (1, 7), (1, 8),  -- John Smith: Java, Spring Boot, Hibernate, MySQL
(2, 19), (2, 20), (2, 15),       -- Sarah Johnson: Digital Marketing, Sales Strategy, Project Management
(3, 1), (3, 2), (3, 6), (3, 10), -- Michael Brown: Java, Python, Spring Boot, AWS
(4, 16), (4, 17), (4, 8),        -- Emily Davis: Data Analysis, Machine Learning, MySQL
(5, 1), (5, 6), (5, 7), (5, 11), -- David Wilson: Java, Spring Boot, Hibernate, Docker
(6, 15), (6, 13), (6, 14),       -- Lisa Anderson: Project Management, Agile, Scrum
(7, 20), (7, 19), (7, 15),       -- Robert Taylor: Sales Strategy, Digital Marketing, Project Management
(8, 19), (8, 4), (8, 5),         -- Jennifer Martinez: Digital Marketing, React, Angular
(9, 1), (9, 2), (9, 10), (9, 12), -- Christopher Garcia: Java, Python, AWS, Kubernetes
(10, 15), (10, 13), (10, 14);    -- Amanda Rodriguez: Project Management, Agile, Scrum 