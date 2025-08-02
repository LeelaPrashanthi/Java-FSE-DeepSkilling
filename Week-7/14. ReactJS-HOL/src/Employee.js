class Employee {
  constructor(id, name, email, phone) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.phone = phone;
  }
}

export default Employee;

export const EmployeesData = [
  new Employee(101, 'Jojo', 'jojo@cognizant.com', '98238971234'),
  new Employee(102, 'Sam', 'sam@cognizant.com', '9981184126'),
  new Employee(103, 'Elisa', 'elisa@cognizant.com', '9989389735')
];
