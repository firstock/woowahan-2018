import { _, boardUtils, API } from './support/Utils.js';

class BoardsController {
    domLoaded(callback) {
        if (window.location.pathname !== "/boards.html") {
            return;
        }

        document.addEventListener("DOMcontentLoaded", this.getBoards(callback))
    }

    getBoards(callback) {
        _.ajax(API.BOARDS.MYBOARD, "GET").then(callback);
    }

    saveBoard(callback) {
        const nameDom = _.$('.board-name');
        const parameters = {
            "name": nameDom.value
        }

        _.ajax(API.BOARDS.MYBOARD, "POST", parameters).then(callback);
    }

}

class BoardsViewHandler {
    constructor() {
        this.modalDiv = _.$('#modal');
    }

    toggleModal() {
        this.modalDiv.classList.toggle('open');
    }

    printBoards(res) {
        const boards = res.content.boards;
        const boardListDom = _.$('.board-list');
        boards.forEach((item) => {
            boardListDom.innerHTML += boardUtils.createTemplate(Template.board, {'id': item.id, 'name': item.name});
        })
    }

    appendBoard(res) {
        const status = res.status;
        const nameDom = _.$('.board-name');
        const boardListDom = _.$('.board-list');

        if (status === "OK") {
            boardListDom.insertAdjacentHTML('beforeend', boardUtils.createTemplate(Template.board, {'id' : res.content.id, 'name': res.content.name}));
            this.toggleModal();
            nameDom.value = "";
        } else {
            const warning = _.$('.warning');
            warning.innerHTML = res.message;
            warning.style.display = 'block';
        }
    }

}


export { BoardsController, BoardsViewHandler };