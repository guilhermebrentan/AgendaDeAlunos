const express = require('express'); 
const app = express();
const bodyParser = require('body-parser'); 
const port = 3000; //porta padrão const 
const sql = require('mssql');
const connStr = "Server=regulus.cotuca.unicamp.br;Database=BD19178;User Id=bd19178;Password=uma senha complicada;"

sql.connect(connStr)
   .then(conn => {console.log("Conexão bem sucedida!"); global.conn = conn})
   .catch(err => console.log("Ocorreu um erro: " + err));


app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());

const router = express.Router();

router.get("/", (req,res) => res.json({"mensagem":"A API está online"}));
app.use("/", router); //define a rota padrão

app.listen(port);
console.log("O servidor está online!");

router.get("/getAluno/:RA?",(req,res) => {
    //pega o valor do RA
    var ra = parseInt(req.params.RA);
    var query;

    //se não tem nenhum RA, retorna todos
    if(!ra)
    {
        query = "Select * from Aluno";

        global.conn.query(query)
                             .then(result => res.json(result.recordset))
                             .catch(err => res.json(err));
    }
    //se tem, retorna o aluno referente ao RA
    else
    {                                               //ra que esta na url
        query = "Select * from Aluno where RA = " + ra.toString();

        global.conn.query(query)
                             .then(result => res.json(result.recordset))
                             .catch(err => res.json(err));
    }
});

router.get("/deleteAluno/:RA",(req,res)=>{
    //pega o valor do RA
    var ra = parseInt(req.params.RA);
    var query;

    
    query  = "Delete from aluno where RA = " + ra.toString();
    global.conn.query(query)
               .then(result => 
                {
                    //se alterou alguma linha, quer dizer que excluiu
                    if(result.rowsAffected != 0)
                    {
                        res.json({"mensagem":"Registro de aluno excupido com sucesso"});
                    }
                    //se não
                    else
                    {
                        res.json({"mensagem":"RA inválido. Nenhum registro excluído"});
                    }
                }).catch(err => res.json(err));   
});

router.get("/insertAluno/:RA/:NOME/:EMAIL",(req,res)=>{
    var ra = parseInt(req.params.RA);
    var nome = req.params.NOME.toString();
    var email = req.params.EMAIL.toString();
    
    var query = "insert into Aluno values ('"+ra+"','"+nome+"', '"+email+"')";

    global.conn.query(query)
    .then(result => res.json({"mensagem":"Registro de aluno cadastrado com sucesso"}))
    .catch(err => res.json({"mensagem":"Dados inválidos, tente novamente"}));            
});

router.get("/updateAluno/:RA/:NOME/:EMAIL",(req,res)=>{
    var ra = parseInt(req.params.RA);
    var nome = req.params.NOME.toString();
    var email = req.params.EMAIL.toString();
    
    var query = "update Aluno set nome = '"+nome+"', email = '"+email+"' where ra = "+ra+"";

    global.conn.query(query)
    .then(result => res.json({"mensagem":"Registro de aluno atualizado com sucesso"}))
    .catch(err => res.json({"mensagem":"Dados inválidos, tente novamente"}));
});