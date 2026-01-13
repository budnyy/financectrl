document.addEventListener("DOMContentLoaded", init)

function init(){
    get(currUID);
    const saveForm = document.getElementById("expenseOp");
    saveForm.addEventListener('submit', handleFormSubmit);
    const getMonthForm = document.getElementById("changeMonth");
    getMonthForm.addEventListener('submit', handleChangeMonth);
}

function handleFormSubmit(evt){
    evt.preventDefault();

    let descr = document.querySelector("#descrExp").value;
    let value  = document.querySelector("#valueExp").value;
    let date = document.querySelector("#dateExp").value;
    let userId = currUID;
    let id = document.querySelector("#idExp").value;

    if(id === ''){
        save(descr, value, date, userId);
    }
    else{
        put(id, descr, value, date, userId);
    }
}

function updateExpenseRow(id, descr, value, date){
    let row = document.querySelector(`tr[expenseId="${id}"]`);
    if(row){
        let data = row.children;
        data[0].textContent = descr;
        data[1].textContent = "R$" + value;
        data[2].textContent = date;
    }
    else{
        console.log("Error trying to update expense");
    }
}

function deleteExpenseRow(id){
    let row = document.querySelector(`tr[expenseId="${id}"]`);
    try{
        row.remove();
    }catch (e) {
        console.log(e);
    }
}

function renderExpenseRow(expense){
    const table = document.getElementById("getTableBody");
    let expDescr = expense.descr;
    let expValue = expense.value;
    let expDate = expense.date;
    let expId = expense.id;

    let newTr = document.createElement("tr");
    newTr.setAttribute("expenseId", expId);

    let descrTd = document.createElement("td");
    descrTd.innerText = expDescr;
    let valueTd = document.createElement("td");
    valueTd.innerText = "R$" + expValue;
    let dateTd = document.createElement("td");
    dateTd.innerText = expDate;

    let actionsTd = document.createElement("td");
    let editButton = document.createElement("button");
    editButton.innerText = "Edit expense";
    editButton.addEventListener('click',(evt) =>{
        handleEditClick(expense);
    })
    let deleteButton = document.createElement("button");
    deleteButton.innerText = "Remove expense";
    deleteButton.addEventListener('click', (evt) =>{
        remove(expId);
    })

    actionsTd.appendChild(editButton);
    actionsTd.appendChild(deleteButton);

    newTr.appendChild(descrTd);
    newTr.appendChild(valueTd);
    newTr.appendChild(dateTd);
    newTr.appendChild(actionsTd);

    table.appendChild(newTr);
}

function loadTable(expenseList){
    const table = document.getElementById("getTableBody");
    while(table.firstChild){
        table.removeChild(table.firstChild);
    }
    for(i = 0; i < expenseList.length; i++){
        renderExpenseRow(expenseList[i]);
    }
}

function handleEditClick(expense){
    let idInputForm = document.getElementById("idExp");
    let descrInputForm = document.getElementById("descrExp");
    let valueInputForm = document.getElementById("valueExp");
    let dateInputForm = document.getElementById("dateExp");

    idInputForm.value = expense.id;
    descrInputForm.value = expense.descr;
    valueInputForm.value = expense.value;
    dateInputForm.value = expense.date;
}

function resetForm(){
    let idInputForm = document.getElementById("idExp");
    let descrInputForm = document.getElementById("descrExp");
    let valueInputForm = document.getElementById("valueExp");
    let dateInputForm = document.getElementById("dateExp");

    idInputForm.value = "";
    descrInputForm.value = "";
    valueInputForm.value = "";
    dateInputForm.value = "";
}

function handleChangeMonth(evt){
    evt.preventDefault();

    let [year, month] = document.getElementById("monthInput").value.split("-");
    let monthInt = parseInt(month);
    getMonth(currUID, monthInt);
}

function clearMonthButton(){
    get(currUID);
    let monthInput = document.getElementById("monthInput");
    monthInput = "";
}