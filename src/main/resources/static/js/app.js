const submit = document.getElementById("word_reg_submit");

const word = document.getElementById("word_input");
const word_type = document.getElementById("word_type_select");
const word_lang = document.getElementById("word_lang_input");
const word_meaning = document.getElementById("word_meaning_input");

submit.addEventListener('click', async (e) => {
    e.preventDefault()
    try {
        let response = await requestFactory('http://localhost:8080/word', 'POST', {
            'word': word.value,
            'type': word_type.options[word_type.selectedIndex].value,
            'language': word_lang.value,
            'meaning': word_meaning.value
        })
        console.log(response)
    } catch (error) {
        console.log(error)
    }
    document.querySelector("form").reset()
})

const requestFactory = async (url, method, data) => {
    let dataReturn
    let init

    if (method === 'GET') {
        init = {
            method: method,
            headers: { 'Content-Type': 'application/json' }
        }
    } else {
        init = {
            method: method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        }
    }

    await fetch(url, init)
        .then(response => response.json())
        .then(dataResponse => dataReturn = dataResponse)

    return dataReturn
}

const settingTypeOptions = async () => {
    let response
    let options = `<option> </option>`
    let select = document.querySelector("#word_type_select")     

    try{
        response = await requestFactory("http://localhost:8080/word/types", 'GET')
        if(response.length){
            options=  Object.values(response).map(type => `<option value=${type}>${type}</option>`).join('')
        }    
    }catch(error){
        console.error(error)
    }

    select.innerHTML= options
}

const initialization = async()=>{
    await settingTypeOptions()
}

document.body.onload= initialization()