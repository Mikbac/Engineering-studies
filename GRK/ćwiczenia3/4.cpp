#include "glew.h"
#include "freeglut.h"
#include "glm.hpp"
#include "ext.hpp"
#include <iostream>
#include <cmath>

#include "Shader_Loader.h"
#include "Render_Utils.h"
#include "Camera.h"

GLuint program;
Core::Shader_Loader shaderLoader;

obj::Model shipModel;
obj::Model sphereModel;

float cameraAngle = 0;
glm::vec3 cameraPos = glm::vec3(-5, 0, 0);
glm::vec3 cameraDir;


void keyboard(unsigned char key, int x, int y)
{
	float angleSpeed = 0.1f;
	float moveSpeed = 0.1f;
	switch (key)
	{
	case 'a': cameraAngle -= angleSpeed; break;
	case 'd': cameraAngle += angleSpeed; break;
	case 'w': cameraPos += cameraDir * moveSpeed; break;
	case 's': cameraPos -= cameraDir * moveSpeed; break;
	}
}

glm::mat4 createCameraMatrix()
{

	cameraDir = glm::vec3(cosf(cameraAngle), 0.0f, sinf(cameraAngle));
	glm::vec3 up = glm::vec3(0, 1, 0);

	return Core::createViewMatrix(cameraPos, cameraDir, up);
}

void drawObject(obj::Model * model, glm::mat4 transformation)
{

	float time = glutGet(GLUT_ELAPSED_TIME) / 1000.0f;

	glm::mat4 camera = createCameraMatrix();
	glm::mat4 perspective = Core::createPerspectiveMatrix();

	transformation = perspective*camera*transformation;

	glUniformMatrix4fv(glGetUniformLocation(program, "transformation"), 1, GL_FALSE, (float*)&transformation);
	glUniform3f(glGetUniformLocation(program, "objectColor"), 0.777, cos(time), 0.222);


	Core::DrawModel(&shipModel);
}

void renderScene()
{

	float time = glutGet(GLUT_ELAPSED_TIME) / 1000.0f;

	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glClearColor(0.0f, 0.3f, 0.3f, 1.0f);

	glUseProgram(program);

	glm::mat4 camera = createCameraMatrix();
	glm::mat4 perspective = Core::createPerspectiveMatrix();
	glm::mat4 transformation;


	/*pierwsza kolumna*/
	transformation[0][0] = 1;
	transformation[0][1] = 0;
	transformation[0][2] = 0;
	transformation[0][3] = 0;
	/*druga kolumna*/
	transformation[1][0] = 0;
	transformation[1][1] = 1;
	transformation[1][2] = 0;
	transformation[1][3] = 0;
	/*trzecia kolumna*/
	transformation[2][0] = 0;
	transformation[2][1] = 0;
	transformation[2][2] = 1;
	transformation[2][3] = 0;
	/*czwarta kolumna*/
	transformation[3][0] = 0;
	transformation[3][1] = 0;
	transformation[3][2] = 0;
	transformation[3][3] = 1;


	drawObject(&shipModel, transformation);



	glUseProgram(0);

	glutSwapBuffers();
}

void init()
{
	glEnable(GL_DEPTH_TEST);
	program = shaderLoader.CreateProgram("shaders/shader_2_1.vert", "shaders/shader_2_1.frag");


	shipModel = obj::loadModelFromFile("models/spaceship.obj");
	sphereModel = obj::loadModelFromFile("models/sphere.obj");


}

void shutdown()
{
	shaderLoader.DeleteProgram(program);
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
	glutCreateWindow("OpenGL Pierwszy Program");
	glewInit();

	init();
	glutKeyboardFunc(keyboard);
	glutDisplayFunc(renderScene);
	glutIdleFunc(idle);

	glutMainLoop();

	shutdown();

	return 0;
}