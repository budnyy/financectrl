//API
async function save(descr, value, date, userId){
    try{
        const saveCall = await fetch('/expense/save', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body:JSON.stringify(descr, value, date, userId)
        })
        const saveCallObj = await saveCall.json();
        renderExpenseRow(saveCallObj);
    }catch(e){
        console.log(e)
    }
}

async function get(userId){
    try{
        const getCall = await fetch(`/expense/get/${userId}`);
        const getCallObj = await getCall.json();
        loadTable(getCallObj);
    }catch (e){
        console.log(e);
    }
}

async function getMonth(userId, month){
    try{
        const getMCall = await fetch(`/expense/get/${userId}/${month}`);
        const getMCallObj = await getMCall.json();
        loadTable(getMCallObj);
    }catch (e){
        console.log(e);
    }
}

async function put(descr, value, date, userId, id){
    try{
        const updateCall = await fetch('/expense/update', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body:JSON.stringify(descr, value, date, userId, id)
        })
        const updateCallObj = await updateCall.json();
        renderExpenseRow(updateCallObj);
    }catch(e){
        console.log(e)
    }
}

async function remove(id){
    try{
        await fetch('/expense/delete', {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body:JSON.stringify(id)
        })
        deleteExpenseRow(id);
    }catch(e){
        console.log(e)
    }
}

function downloadCsv(){}

//UI

document.addEventListener("DOMContentLoaded", init)

function init(){
    get(currUID);
    handleFormSubmit();
    handleEditClick();
    handleRemoveClick();
}

function handleFormSubmit(){
    const saveForm = document.getElementById("expenseOp");

    let descr = saveForm.querySelector('[name = "descrForm"]').value;
    let value  = saveForm.querySelector('[name = "valueForm"]').value;
    let date = saveForm.querySelector('[name = "dateForm"]').value;
    let userId = currUID;
    let id = saveForm.querySelector('[name="idForm"]').value;

    saveForm.addEventListener('submit', (evt) =>{
        evt.preventDefault();

        if(id === ""){
            save(descr, value, date, userId);
        }
        else{
            put(descr, value, date, userId, id);
        }
    });
}

function deleteExpenseRow(id){
    const table = document.getElementById("getTableBody");
    let tr = document.querySelectorAll(`[expenseId = ${id}]`)
    table.removeChild(tr)
}

function renderExpenseRow(expense){
    const table = document.getElementById("getTableBody");
    expDescr = expense.descr;
    expValue = expense.value;
    expDate = expense.date;
    expId = expense.id;

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
    editButton.setAttribute("action","edit");
    let deleteButton = document.createElement("button");
    deleteButton.setAttribute("action", "delete");

    actionsTd.appendChild(editButton);
    actionsTd.appendChild(deleteButton);

    newTr.appendChild(descrTd);
    newTr.appendChild(valueTd);
    newTr.appendChild(dateTd);
    newTr.appendChild(actionsTd);

    table.appendChild(newTr);
}

function loadTable(expenseList){
    while(table.firstChild){
        table.removeChild(table.firstChild);
    }
    for(i = 0; i < expenseList.length; i++){
        renderExpenseRow(expenseList[i]);
    }
}

function handleEditClick(){}

function handleRemoveClick(){}