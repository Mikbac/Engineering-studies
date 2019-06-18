#include "glew.h"
#include "freeglut.h"
#include "glm.hpp"
#include "ext.hpp"
#include <iostream>
#include <cmath>

#include "Shader_Loader.h"
#include "Render_Utils.h"

#include "Box.cpp"

GLuint program;
Core::Shader_Loader shaderLoader;

glm::mat4 createPerspectiveMatrix()
{
	const float zNear = 0.1f, zFar = 10.0f;
	const float frustumScale = 1.0f;
	glm::mat4 perspective;
	perspective[0][0] = frustumScale;
	perspective[1][1] = frustumScale;
	perspective[2][2] = (zFar + zNear) / (zNear - zFar);
	perspective[3][2] = (2 * zFar * zNear) / (zNear - zFar);
	perspective[2][3] = -1;
	perspective[3][3] = 0;

	return perspective;
}

void renderScene()
{
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glClearColor(0.0f, 0.3f, 0.3f, 1.0f);
	float time = glutGet(GLUT_ELAPSED_TIME) / 1000.0f;
	glUseProgram(program);
	glm::mat4 createPerspectiveMatrix();
	glm::mat4 transformation;
	glm::mat4 transform;

	


	transformation = createPerspectiveMatrix() * transform;
	glUniformMatrix4fv(glGetUniformLocation(program, "transformation"), 1, GL_FALSE, (float*)&transformation);

	// ------------------------------------------

	const float vertices[] = {
		0.25f,  0.25f, 0.0f, 1.0f,
		0.25f, -0.25f, 0.0f, 1.0f,
		-0.25f, -0.25f, 0.0f, 1.0f
	};

	Core::VertexData vertexData;
	vertexData.NumActiveAttribs = 2;				// Liczba uzywanych atrybutow wierzcholka
	vertexData.Attribs[0].Pointer = boxPositions;	// Wskaznik na dane zerowego atrybutu
	vertexData.Attribs[0].Size = 4;					// Wielkosc zerowego atrybutu (ilosc liczb opisujacych ten atrybut w pojedynczym wierzcholku)
	vertexData.Attribs[1].Pointer = boxColors;		// Wskaznik na dane zerowego atrybutu
	vertexData.Attribs[1].Size = 4;
	vertexData.NumVertices = 36;					// Liczba wierzcholkow do narysowania
	Core::DrawVertexArray(vertexData);



	// ------------------------------------------

	glUseProgram(0);
	glutSwapBuffers();
}

void init()
{
	glEnable(GL_DEPTH_TEST);
	program = shaderLoader.CreateProgram("shaders/shader_2_1.vert", "shaders/shader_2_1.frag");
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
	glutDisplayFunc(renderScene);
	glutIdleFunc(idle);

	glutMainLoop();

	shutdown();

	return 0;
}