#include "glew.h"
#include "freeglut.h"
#include "glm.hpp"
#include "ext.hpp"
#include <iostream>
#include <cmath>
#include <vector>

#include "Shader_Loader.h"
#include "Render_Utils.h"
#include "Camera.h"
#include "Texture.h"
#include <time.h>

GLuint programColor;
GLuint programTexture;






Core::Shader_Loader shaderLoader;

obj::Model shipModel;
obj::Model sphereModel;
obj::Model groundModel;
obj::Model rockModel;
obj::Model rock1Model;
obj::Model rock2Model;
obj::Model flowerModel;
obj::Model flower2Model;
obj::Model flower3Model;


glm::vec3 cameraPos = glm::vec3(0, 0, 5);
glm::vec3 cameraDir; // Wektor "do przodu" kamery
glm::vec3 cameraSide; // Wektor "w bok" kamery
float cameraAngle = 0;

glm::mat4 cameraMatrix, perspectiveMatrix;

glm::vec3 lightDir = glm::normalize(glm::vec3(1.0f, -0.9f, -1.0f));

glm::quat rotation = glm::quat(1, 0, 0, 0);
glm::vec3 EulerAngles(90, 45, 0);
glm::quat MyQuaterion = glm::angleAxis(10.0f * 15.0f, glm::vec3(-1.0f, 0.0f, 0.0f));

GLuint textureGround;
GLuint textureAsteroid;
GLuint textureFish;
GLuint textureRock;
const int asteroidsTransSize = 70;
glm::vec3 asteroidsTrans[asteroidsTransSize];

glm::vec2 mouseOldCords;
glm::vec2 mouseDiff;

float mouseSensitivity = 0.01f;


//----------
bool tryb = true;
glm::vec3 cameraPos2;
float i = 0.0;
glm::quat rotation2;
float ac = 0.0;


//----------
int move = 0;
void keyboard(unsigned char key, int x, int y)
{

	float angleSpeed = 10.0f;
	float moveSpeed = 0.1f;
	glm::quat quatZ;
	switch (key)
	{
	case 'z': tryb = true;
		quatZ = glm::angleAxis(angleSpeed * mouseSensitivity, glm::vec3(0.0f, 0.0f, -1.0f));
		rotation = glm::normalize(quatZ * rotation);
		cameraDir = glm::inverse(rotation) * glm::vec3(0.0f, 0.0f, -1.0f);
		cameraSide = glm::inverse(rotation) * glm::vec3(1.0f, 0.0f, 0.0f);
		break;
	case 'x': tryb = true;
		quatZ = glm::angleAxis(angleSpeed * mouseSensitivity, glm::vec3(0.0f, 0.0f, 1.0f));
		rotation = glm::normalize(quatZ * rotation);
		cameraDir = glm::inverse(rotation) * glm::vec3(0.0f, 0.0f, -1.0f);
		cameraSide = glm::inverse(rotation) * glm::vec3(1.0f, 0.0f, 0.0f);
		break;
	case 'w':tryb = true; cameraPos += cameraDir * moveSpeed; break;
	case 's':tryb = true; cameraPos -= cameraDir * moveSpeed; break;
	case 'd':tryb = true; cameraPos += cameraSide * moveSpeed; break;
	case 'a':tryb = true; cameraPos -= cameraSide * moveSpeed; break;
	case 'r':
		tryb = false;
		cameraPos2 = cameraPos;
		i = i + 1.0;
		cameraPos2[2] += sin(i / 10);
		cameraPos2[0] += cos(i / 10);
		ac = 1;
		break;
	}
}

void mouse(int x, int y)
{
	mouseDiff.x += mouseOldCords.x - x;
	mouseDiff.y += mouseOldCords.y - y;
	mouseOldCords.x = x;
	mouseOldCords.y = y;
}

glm::mat4 createCameraMatrix()
{
	if (tryb) {
		/* klawisze */
		cameraDir = glm::vec3(cosf(cameraAngle - glm::radians(90.0f)), 0.0f, sinf(cameraAngle - glm::radians(90.0f)));
		glm::vec3 up = glm::vec3(0, 1, 0);
		cameraSide = glm::cross(cameraDir, up);
		/* --- */

		//return Core::createViewMatrix(cameraPos, cameraDir, up);
		glm::quat quatX = glm::angleAxis(mouseDiff.y * mouseSensitivity, glm::vec3(-1.0f, 0.0f, 0.0f));
		glm::quat quatY = glm::angleAxis(mouseDiff.x * mouseSensitivity, glm::vec3(0.0f, -1.0f, 0.0f));
		glm::quat rotationChange = quatY*quatX;
		mouseDiff.x = 0.0f;
		mouseDiff.y = 0.0f;
		rotation = glm::normalize(rotationChange * rotation);
		cameraDir = glm::inverse(rotation) * glm::vec3(0.0f, 0.0f, -1.0f);
		cameraSide = glm::inverse(rotation) * glm::vec3(1.0f, 0.0f, 0.0f);
		return Core::createViewMatrixQuat(cameraPos, rotation);
	}
	else
	{
		glm::quat quatY = glm::angleAxis(ac *(-0.0886370f - float(((int(i) % 3) *0.01))), glm::vec3(0.0f, -1.0f, 0.0f));
		glm::quat rotationChange2 = quatY * 1.0f;
		ac = 0.0f;
		glm::vec3 up = glm::vec3(0, 0, 0);
		rotation2 = glm::normalize(rotationChange2 * rotation2);
		//return Core::createViewMatrix(cameraPos2, cameraDir, up);
		return Core::createViewMatrixQuat(cameraPos2, rotation2);
	}
}

void drawObjectColor(obj::Model * model, glm::mat4 modelMatrix, glm::vec3 color)
{
	GLuint program = programColor;

	glUseProgram(program);

	glUniform3f(glGetUniformLocation(program, "objectColor"), color.x, color.y, color.z);
	glUniform3f(glGetUniformLocation(program, "lightDir"), lightDir.x, lightDir.y, lightDir.z);

	glm::mat4 transformation = perspectiveMatrix * cameraMatrix * modelMatrix;
	glUniformMatrix4fv(glGetUniformLocation(program, "modelViewProjectionMatrix"), 1, GL_FALSE, (float*)&transformation);
	glUniformMatrix4fv(glGetUniformLocation(program, "modelMatrix"), 1, GL_FALSE, (float*)&modelMatrix);

	Core::DrawModel(model);

	glUseProgram(0);
}

void drawObjectTexture(obj::Model * model, glm::mat4 modelMatrix, GLuint textureId)
{
	GLuint program = programTexture;

	glUseProgram(program);

	glUniform3f(glGetUniformLocation(program, "lightDir"), lightDir.x, lightDir.y, lightDir.z);
	Core::SetActiveTexture(textureId, "textureSampler", program, 0);

	glm::mat4 transformation = perspectiveMatrix * cameraMatrix * modelMatrix;
	glUniformMatrix4fv(glGetUniformLocation(program, "modelViewProjectionMatrix"), 1, GL_FALSE, (float*)&transformation);
	glUniformMatrix4fv(glGetUniformLocation(program, "modelMatrix"), 1, GL_FALSE, (float*)&modelMatrix);

	Core::DrawModel(model);

	glUseProgram(0);
}
void renderScene()
{
	float time = glutGet(GLUT_ELAPSED_TIME) / 1000.0f;


	// Aktualizacja macierzy widoku i rzutowania
	cameraMatrix = createCameraMatrix();
	perspectiveMatrix = Core::createPerspectiveMatrix();

	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glClearColor(0.0f, 0.2f, 0.4f, 1.0f);

	glm::mat4 shipInitialTransformation = glm::translate(glm::vec3(0, -0.25f, 0)) * glm::rotate(glm::radians(180.0f), glm::vec3(0, 1, 0)) * glm::scale(glm::vec3(0.25f));
	glm::mat4 shipModelMatrix = glm::translate(cameraPos + cameraDir * 0.5f) * glm::mat4_cast(glm::inverse(rotation)) * shipInitialTransformation;
	glm::mat4 groundModelMatrix = glm::translate(glm::vec3(0, -1.25f, 0)) * glm::rotate(glm::radians(60.0f), glm::vec3(0, 0, 1)) * /*glm::mat4_cast(glm::inverse(rotation))* */glm::scale(glm::vec3(20, 0.15, 20));
	glm::mat4 rockModelMatrix = glm::translate(glm::vec3(0, 0.25f, 0)) * glm::rotate(90.0f, glm::vec3(0, 1, 0)) * /*glm::mat4_cast(glm::inverse(rotation))* */glm::scale(glm::vec3(1, 1, 1));
	glm::mat4 rock1ModelMatrix = glm::translate(glm::vec3(-14, -22.25f, 10)) * glm::rotate(90.0f, glm::vec3(0, 1, 0)) * /*glm::mat4_cast(glm::inverse(rotation))* */glm::scale(glm::vec3(0.75, 1, 0.9));
	glm::mat4 rock2ModelMatrix = glm::translate(glm::vec3(12, 20.25f, -8)) * glm::rotate(90.0f, glm::vec3(0, 1, 0)) * /*glm::mat4_cast(glm::inverse(rotation))* */glm::scale(glm::vec3(1, 1, 1.2));
	glm::mat4 flowerModelMatrix = glm::translate(glm::vec3(-7, -14.25f, 0)) * glm::rotate(240.0f, glm::vec3(0, 0, 1)) * /*glm::mat4_cast(glm::inverse(rotation))* */glm::scale(glm::vec3(1, 1, 1));
	glm::mat4 flower2ModelMatrix = glm::translate(glm::vec3(8, 12.25f, 0)) * glm::rotate(240.0f, glm::vec3(0, 0, 1)) * /*glm::mat4_cast(glm::inverse(rotation))* */glm::scale(glm::vec3(0.8, 0.8, 0.7));
	glm::mat4 flower3ModelMatrix = glm::translate(glm::vec3(4, 5.75f, 14)) * glm::rotate(240.0f, glm::vec3(0, 0, 1)) * /*glm::mat4_cast(glm::inverse(rotation))* */glm::scale(glm::vec3(0.5, 0.5, 0.5));
	drawObjectTexture(&groundModel, groundModelMatrix, textureGround);
	drawObjectTexture(&rockModel, rockModelMatrix, textureRock);
	drawObjectTexture(&rock1Model, rock1ModelMatrix, textureRock);
	drawObjectTexture(&rock2Model, rock2ModelMatrix, textureRock);
	drawObjectColor(&flowerModel, flowerModelMatrix, glm::vec3(0.6f, 2.0f, 0.2f));
	drawObjectColor(&flower2Model, flower2ModelMatrix, glm::vec3(2.6f, 0.8f, 0.2f));
	drawObjectColor(&flower3Model, flower3ModelMatrix, glm::vec3(0.6f, 0.8f, 2.0f));
	//drawObjectColor(&shipModel, shipModelMatrix, glm::vec3(0.6f));
	drawObjectTexture(&shipModel, shipModelMatrix, textureFish);
	for (int i = 0; i < asteroidsTransSize; i++) {

		//asteroidsTrans[i] = glm::vec3(0.0, time, 0.0);
		srand((1));
		float  r;
		if (move % (500 + i) == 0)
			if ((rand() % 2) == 0)r = (-1.0); else r = (1.0);
		asteroidsTrans[i][0] += time / 10000 * r;
		if (move % (500 + i) == 0)
			if ((rand() % 2) == 0)r = (-1.0); else r = (1.0);
		asteroidsTrans[i][1] += time / 10000 * r;
		if (move % (500 + i) == 0)
			if ((rand() % 2) == 0)r = (-1.0); else r = (1.0);
		asteroidsTrans[i][2] += time / 10000 * r;
		move++;
		//asteroidsTrans[i][2] += time / 100000;
		drawObjectTexture(&sphereModel, glm::translate(asteroidsTrans[i]), textureAsteroid);

	}

	glutSwapBuffers();
}

void init()
{
	srand(time(0));
	glEnable(GL_DEPTH_TEST);
	programColor = shaderLoader.CreateProgram("shaders/shader_color.vert", "shaders/shader_color.frag");
	programTexture = shaderLoader.CreateProgram("shaders/shader_tex.vert", "shaders/shader_tex.frag");
	sphereModel = obj::loadModelFromFile("models/sphere.obj");
	shipModel = obj::loadModelFromFile("models/spaceship.obj");
	groundModel = obj::loadModelFromFile("models/ground2.obj");
	rockModel = obj::loadModelFromFile("models/rock2.obj");
	rock1Model = obj::loadModelFromFile("models/rock2.obj");
	rock2Model = obj::loadModelFromFile("models/rock2.obj");
	flowerModel = obj::loadModelFromFile("models/flower3.obj");
	flower2Model = obj::loadModelFromFile("models/flower3.obj");
	flower3Model = obj::loadModelFromFile("models/flower3.obj");
	textureAsteroid = Core::LoadTexture("textures/asteroid.png");
	textureFish = Core::LoadTexture("textures/fish.png");
	textureGround = Core::LoadTexture("textures/dirt.png");
	textureRock = Core::LoadTexture("textures/rock2.png");
	for (int i = 0; i < asteroidsTransSize; i++) {
		asteroidsTrans[i] = glm::ballRand(30.0f);
	}
}

void shutdown()
{
	shaderLoader.DeleteProgram(programColor);
	shaderLoader.DeleteProgram(programTexture);
}

void idle()
{
	glutPostRedisplay();
}

int main(int argc, char ** argv)
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DEPTH | GLUT_DOUBLE | GLUT_RGBA);
	glutInitWindowPosition(200, 200);
	glutInitWindowSize(600, 600);
	glutCreateWindow("Projekt GRK");
	glewInit();

	init();
	glutKeyboardFunc(keyboard);
	glutPassiveMotionFunc(mouse);
	glutDisplayFunc(renderScene);
	glutIdleFunc(idle);

	glutMainLoop();

	shutdown();

	return 0;
}