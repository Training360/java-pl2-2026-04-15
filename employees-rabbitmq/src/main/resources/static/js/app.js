window.onload = function() {
    findEmployees();

    document.querySelector("#join-button").onclick = join;
}

function findEmployees() {
    fetch('/api/employees')
        .then(response => response.json())
        .then(employees => print(employees));
}

function print(employees) {
    let table = document.querySelector("#employees-table");
    let rows = "";
    for (let employee of employees) {
        rows +=  `<tr><td>${employee.id}</td><td>${employee.name}</td></tr>`;
    }
    table.innerHTML = rows;
}

function join() {
    const nameInput = document.querySelector("#name-input");
    const name = nameInput.value;
    const jsonData = JSON.stringify({"name": name});

    fetch("/api/employees", {
        method: "POST",
        body: jsonData,
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => response.json())
        .then(employee => findEmployees())
    ;

    nameInput.value = "";

    return false;
}