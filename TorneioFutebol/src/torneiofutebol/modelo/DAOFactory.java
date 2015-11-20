/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneiofutebol.modelo;

import torneiofutebol.modelo.pg.PostgreSqlDAOFactory;

/**
 *
 * @author alexromanelli
 */
public abstract class DAOFactory {
    public static final int POSTGRESQL = 1;
    public static final int MYSQL = 2;
    public static final int WEB = 3;
    
    private static DAOFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case POSTGRESQL:
                return new PostgreSqlDAOFactory();
            case MYSQL:
            case WEB:
            default:
                return null;
        }
    }
    
    public static DAOFactory getDefaultDAOFactory() {
        return getDAOFactory(DAOFactory.POSTGRESQL);
    }

    public abstract JogadorDAO getJogadorDAO();
    public abstract ArbitroDAO getArbitroDAO();
    public abstract TecnicoDAO getTecnicoDAO();
    public abstract TimeFutebolDAO getTimeFutebolDAO();
    public abstract PosicaoJogadorDAO getPosicaoJogadorDAO();
    public abstract OrganizacaoArbitragemDAO getOrganizacaoArbitragemDAO();
}
