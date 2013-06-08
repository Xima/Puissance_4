package com.xima.puissance4.views;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.xima.puissance4.MyGestureListener;

public class World implements ApplicationListener {

    private int 							width;
    private int 							height;
    public  int								caseSize;
    private int								panelSizeX = 2048 + caseSize;
    private int								panelSizeY = 2048 + caseSize;
    private int								grille = 33;
    private int								we;
    private int								qw;

    private OrthographicCamera     			cam;
    private Texture                         bgT, pionT;
    private Sprite							bgS,pionS;

    private Rectangle                       glViewport;
    private ShapeRenderer					shapeRenderer;
    private SpriteBatch						batch;
    private Rectangle						touchAreaN, touchAreaE, touchAreaS, touchAreaO;

    @Override
    public void create() {
    	//Création du listener de Gesture
    	Gdx.input.setInputProcessor(new GestureDetector(new MyGestureListener(this)));
        load(); 
        //Initialisation des outils de dessin
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        touchAreaN = new Rectangle();
        touchAreaE = new Rectangle();
        touchAreaS = new Rectangle();
        touchAreaO = new Rectangle();
    }
    
    public void load() {
    	//Chargement des textures
    	bgT = new Texture(Gdx.files.internal("data/bg1.png"));
    	bgT.setWrap(TextureWrap.ClampToEdge, TextureWrap.ClampToEdge);
        pionT = new Texture(Gdx.files.internal("data/pion.png"));
        //créations des Sprites
        bgS = new Sprite(bgT);
        bgS.setPosition(0, 0);
        bgS.setSize(panelSizeX, panelSizeY);
        pionS = new Sprite(pionT);
        pionS.setPosition(2048, 64);
        pionS.setSize(64, 64);
        pionS.setColor(1, 0, 0, 1);
    }
    
    @Override
    public void resize(int width, int height) {
    	this.width = width;
    	this.height = height;
    	if (cam == null)
            cam = new OrthographicCamera(width, height);     
    	//regle les dimension du cadre de la caméra
    	cam.setToOrtho(false, width, height);
        //regle la position de la caméra
        cam.position.set(panelSizeX/2, panelSizeY/2, 0);
        //regle la taille de l'affichage : ça doit être l'écran entier
        if (glViewport == null)
        	glViewport = new Rectangle(0, 0, width, height);
        glViewport.set(0, 0, width, height);
        
        touchAreaN.set(50, height-50, width-50*2, 50);
        touchAreaE.set(width-50, 50, 50, height-50*2);
        touchAreaS.set(50, 0, width-50*2, 50);
        touchAreaO.set(0, 50, 50, height-50*2);
    }

    public void loop(float delta) {
    	// TODO
    }
    
    @Override
    public void render() {
            GL10 gl = Gdx.graphics.getGL10();     
            // Camera --------------------- /

            gl.glClearColor(0, 1, 0, 1);
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
            gl.glViewport((int) glViewport.x, (int) glViewport.y,
                            (int) glViewport.width, (int) glViewport.height);      
            cam.update();
            cam.apply(gl);
            // Texturing --------------------- /
            gl.glActiveTexture(GL10.GL_TEXTURE0);
            gl.glEnable(GL10.GL_TEXTURE_2D);
            
            bgT.bind();
            batch.setProjectionMatrix(cam.combined);
            batch.begin();
           // batch.draw(bgT, 0, 0);
            bgS.draw(batch);
            pionS.draw(batch);
            batch.end();
           // shapeRenderer.setProjectionMatrix(cam.projection);
            shapeRenderer.begin(ShapeType.Rectangle);
            shapeRenderer.setColor(1, 0, 0, 1);
            rRect(touchAreaN);
            rRect(touchAreaE);
            rRect(touchAreaS);
            rRect(touchAreaO);
            shapeRenderer.end();
    }
public void zoom(double d) {
	if (cam.zoom + d > 10)cam.zoom = 10;
	else if (cam.zoom + d < 1) cam.zoom = 1;
	else cam.zoom += d;
	System.out.println("zoom : " + cam.zoom);
}

public void drag(float amountX, float amountY) {
	if (cam.position.x + cam.zoom*amountX < 0) cam.position.x = 0;
	else if (cam.position.x + amountX > panelSizeX) cam.position.x = panelSizeX;
	else cam.translate(cam.zoom*amountX, 0, 0);
	 if (cam.position.y + cam.zoom*amountY < 0) cam.position.y = 0;
	else if (cam.position.y + cam.zoom*amountY > panelSizeY) cam.position.y = panelSizeY;
	else cam.translate(0, cam.zoom*amountY, 0);
}

public boolean isOnTouchArea(float x, float y, int[] returnValues) {
		if (touchAreaN.contains(x, y)) {
			returnValues[0] = MyGestureListener.N;
			return true;
		}
		if (touchAreaE.contains(x, y)) {
			returnValues[0] = MyGestureListener.E;
			return true;
		}
		if (touchAreaS.contains(x, y)) {
			returnValues[0] = MyGestureListener.S;
			return true;
		}
		if (touchAreaO.contains(x, y)) {
			returnValues[0] = MyGestureListener.O;
			return true;
		}
		return false;
}

public void drawLine(float x, float y) {
	Gdx.app.log("World", "drawLine methode");
	// TODO
}
public Vector3 project(Vector3 vec) {
	cam.project(vec);
	return vec;
}

public Vector3 unproject(Vector3 vec) {
	cam.unproject(vec);
	return vec;
}
public void rRect(Rectangle r) {
	shapeRenderer.rect(r.x, r.y, r.width, r.height);
}
    @Override
    public void resume() {
            // TODO Auto-generated method stub
    }

    @Override
    public void dispose() {
            batch.dispose();
            bgT.dispose();
            shapeRenderer.dispose();
            
    }

    @Override
    public void pause() {
            // TODO Auto-generated method stub
    }


}