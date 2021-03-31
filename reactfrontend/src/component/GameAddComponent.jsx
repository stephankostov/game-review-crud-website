import React, { Component } from "react";
import DeveloperAPIService from "../service/DeveloperAPIService";
import GameAPIService from "../service/GameAPIService";
import LanguageAPIService from "../service/LanguageAPIService";

class GameAddComponent extends Component {

    constructor(props) {
        super(props);
        this.state = {
            gameId: 9999,
            name: '',
            developerId: 1,
            languageId: 1,
            developerList: [],
            languageList: [],
            message: null
        }
        this.refreshDeveloperList = this.refreshDeveloperList.bind(this);
        this.refreshLanguageList = this.refreshLanguageList.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount() {
        this.refreshDeveloperList();
        this.refreshLanguageList();
    }

    refreshDeveloperList() {
        DeveloperAPIService.retrieveAllDevelopers()
            .then(response => {
                    this.setState({ developerList: response.data });
                });
    }

    refreshLanguageList() {
        LanguageAPIService.retrieveAllLanguages()
            .then(response => {
                this.setState( {languageList: response.data})
            });
    }

    addDeveloperPage() {
        this.props.history.push("developer-add")
    }

    onChange = (e) => {
        this.setState({[e.target.name]: e.target.value})
    }

    async handleSubmit(event) {
        event.preventDefault();
        let developer = await DeveloperAPIService.retrieveDeveloper(this.state.developerId)
            .then(response => response.data)
        let language = await LanguageAPIService.retrieveLanguage(this.state.languageId)
            .then(response => response.data)
        let game = {gameId: this.state.gameId, name: this.state.name, developer: developer, language: language}
        GameAPIService.createGame(game)
            .then((response) => {
                console.log(response)
                this.setState({message: 'Game Added Successfully.'})
                this.props.history.push('/games')
            });
    }

    render() {
        return(
            <div>
                <h2>Add Game</h2>

                <form>

                    <div className="form-group">
                        <label>Name:</label>
                        <input type="text" placeholder="name" name="name"
                               className="form-control" value={this.state.name} onChange={this.onChange}/>
                    </div>

                    <div className="form-group">
                        <label>Developer:</label>
                        <select onChange={this.onChange} name="developerId" >
                            {this.state.developerList.map(
                                developer =>
                                    <option key={developer.developerId} value={developer.developerId}> {developer.name} </option>
                                )
                            }
                        </select>
                        <button className="btn btn-success" onClick={() => this.addDeveloperPage()}>Add New Developer</button>
                    </div>

                    <div className="form-group">
                        <label>Language:</label>
                        <select onChange={this.onChange} name="developerId" >
                            {this.state.languageList.map(
                                language =>
                                    <option key={language.languageId} value={language.languageId}> {language.name} </option>
                                )
                            }
                        </select>
                    </div>

                    <button className="btn btn-success" onClick={this.handleSubmit}>Save</button>

                </form>
            </div>
        )
    }
}

export default GameAddComponent;