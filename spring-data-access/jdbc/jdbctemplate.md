# JdbcTemplate

[Retour](https://github.com/grouault/spring-tutorial/blob/master/spring-data-access/jdbc/README.md)

## Gestion de la connexion:
* par rapport à un projet pur Jdbc, jdbctemplate gère la connexion. 
* On supprime donc la méthode getConnexion
* Eviter de demander la connexion au JdbcTemplate

## Transaction
Plus de gestion des transactions explicit

## Dao
* Toutes les méthodes n'ont pas besoin de la connexion mais y accède via jdbctemplate
* Certaines méthodes prennent en paramètre la connexion (classe anonyme), mais sont appelées par des méthodes de jdbctemplate
```
PreparedStatementCreator psc = new PreparedStatementCreator() {
	@Override
	public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
		return AbstractDAO.this.buildStatementForInsert(pUneEntite, con);
	}
};
```

## RowMapper
Permet de mapper les résultats d'une requête sur une entité
```
public class OperationJdbcMapper implements RowMapper<IOperationEntity> {

	@Override
	public IOperationEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		IOperationEntity result = new OperationEntity();
		result.setId(Integer.valueOf(rs.getInt("id")));
		result.setLibelle(rs.getString("libelle"));
		double vm = rs.getDouble("montant");
		// Le montant etait-il null ?
		boolean mnull = rs.wasNull();
		if (!mnull) {
			result.setMontant(Double.valueOf(vm));
		} else {
			result.setMontant(null);
		}
		result.setDate(rs.getTimestamp("date"));
		result.setCompteId(Integer.valueOf(rs.getInt("compteId")));
		return result;
		
	}

}
```
