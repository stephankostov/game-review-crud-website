import React, { Component } from 'react';
import GameAPIService from "../service/GameAPIService";

class GameEditComponent extends Component {

    constructor(props) {
        super(props);

        this.state = {
            id: this.props.match.params.id,
            name: '',
            developerId: null
        }
        this.loadGame = this.loadGame.bind(this);
    }

    componentDidMount() {
        this.loadGame();
    }

    loadGame() {
        GameAPIService.retrieveGame(this.state.id)
            .then((response) => {
                let game = response.data;
                this.setState({
                    name: game.name,
                    developerId: game.developer.developerId
                })
            });
    }

    onChange = (e) =>
        this.setState({name: e.target.value});

    saveGame = (e) => {
        e.preventDefault();
        GameAPIService.retrieveGame(this.state.id)
            .then(response => {
                let game = response.data;
                console.log(game);
                game.name = this.state.name;
                GameAPIService.updateGame(this.state.id, game)
                    .then(response => {
                        this.setState({message: 'Game edited successfully.'});
                        this.props.history.push(`/games/${this.state.id}`);
                    });
            });
    }

    render() {

        let { id } = this.state

        return (
            <div>
                <h3>Edit Game Details</h3>

                <form>

                    <div className="form-group">
                        <label>ID:</label>
                        <input type="text" placeholder="gameId" name="gameId"
                               className="form-control" readOnly={true} defaultChecked={id}/>
                    </div>

                    <div className="form-group">
                        <label>Name:</label>
                        <input type="text" placeholder="Game Name" name="gamename"
                               className="form-control" value={this.state.name} onChange={this.onChange}/>
                    </div>

                    <button className="btn btn-success" onClick={this.saveGame}>Save</button>
                </form>
            </div>
        )
    }
}

export default GameEditComponent;