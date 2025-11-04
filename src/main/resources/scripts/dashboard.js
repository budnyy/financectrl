//API
function save(descr, value, date, userId){
    fetch('/expense/save',{
        method: 'POST',
        headers:{
            'Content-Type': 'application/json'
        },
        body:JSON.stringify(descr, value, date, userId)
    })
        .then(updateTable(userId))
        .catch(error => console.log(error))
}

function get(userId){
    fetch('expense/home',{
        headers:{
            'Content-Type': 'application/json'
        },
        body:JSON.stringify(userId)
    })
        .then(response => {
            if(response.ok){
                updateTable(userId, response);
            }
            else{
                throw new Error("Something went wrong!");
            }
        })
        .catch(error => console.log(error))
    return response
}

function put(descr, value, date, userId, id){
    fetch('/expense/update',{
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body:JSON.stringify(id, descr, value, date, userId)
    })
        .then(updateTable(userId))
        .catch(error => console.log(error))
}

function remove(userId){
    fetch('expense/delete',{
        headers:{
            'Content-Type': 'application/json'
        },
        body:JSON.stringify(userId)
    })
        .then(response => updateTable(response))
        .catch(error => console.log(error))
}

function downloadCsv(){}

//UI
function handleFormSubmit(){
    const saveForm = document.getElementById("expenseOp");

    let descr = saveForm.querySelector('[name = "descr"]').value;
    let value  = saveForm.querySelector('[name = "value_"]').value;
    let date = saveForm.querySelector('[name = "date"]').value;
    let userId = saveForm.querySelector('[name = "userId"]').value;
    let id = saveForm.querySelector('[name="id_"]').value;

    saveForm.addEventListener('submit', (evt) =>{
        evt.preventDefault();

        if(id == ""){
            save(descr, value, date, userId);
        }
        else{
            put(descr, value, date, userId, id);
        }
    });
}

function renderExpenseRow(id, descr, value, date){
    let newTr = document.createElement("tr");
    newTr.setAttribute("data-expense-id", id);

    let descrTd = document.createElement("td");
    descrTd.innerText = descr;
    let valueTd = document.createElement("td");
    valueTd.innerText = "R$" + value;
    let dateTd = document.createElement("td");
    dateTd.innerText = date;

    newTr.appendChild(descrTd);
    newTr.appendChild(valueTd);
    newTr.appendChild(dateTd);

    return newTr;
}

function updateTable(userId){
    const table = document.getElementById("getTableBody");
    while(table.firstChild){
        table.removeChild(table.firstChild);
    }
    let expList = get(userId)

}

function handleEditClick(){}

function handleRemoveClick(){}