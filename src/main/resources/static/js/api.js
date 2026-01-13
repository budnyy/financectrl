let baseurl = "financectrl"

async function save(descr, value, date, userId){
    try{
        const saveCall = await fetch(`/${baseurl}/expense/save`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body:JSON.stringify({descr, value, date, userId})
        })
        const saveCallObj = await saveCall.json();
        renderExpenseRow(saveCallObj);
        resetForm();
    }catch(e){
        console.log(e)
    }
}

async function get(userId){
    try{
        const getCall = await fetch(`/${baseurl}/expense/get/${userId}`);
        const getCallObj = await getCall.json();
        loadTable(getCallObj);
    }catch (e){
        console.log(e);
    }
}

async function getMonth(userId, month){
    try{
        const getMCall = await fetch(`/${baseurl}/expense/get/${userId}/${month}`);
        const getMCallObj = await getMCall.json();
        loadTable(getMCallObj);
    }catch (e){
        console.log(e);
    }
}

async function put(id, descr, value, date, userId){
    try{
        const updateCall = await fetch(`/${baseurl}/expense/update/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body:JSON.stringify({descr, value, date, userId})
        })
        const updateCallObj = await updateCall.json();
        updateExpenseRow(
            updateCallObj.id,
            updateCallObj.descr,
            updateCallObj.value,
            updateCallObj.date
        );
        resetForm();
    }catch(e){
        console.log(e)
    }
}

async function remove(id){
    try{
        await fetch(`/${baseurl}/expense/delete`, {
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