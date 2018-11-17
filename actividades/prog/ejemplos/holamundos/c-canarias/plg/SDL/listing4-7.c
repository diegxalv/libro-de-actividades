/* Animation in SDL - version 2*/

#include <SDL/SDL.h>
#include <stdio.h>
#include <stdlib.h>

#define NUM_PENGUINS 100
#define MAX_SPEED    1

/* This structure stores the information for one on-screen penguin */
typedef struct penguin_s {
  int x, y;   // position on screen
  int dx, dy; // movement vector
} penguin_t, *penguin_p;

/* Array of penguins */

static penguin_t penguins[NUM_PENGUINS];

/* These are now global variables - for convenience */

static SDL_Surface *screen;
static SDL_Surface *penguin;

/* This routine loops through the array of penguins and sets each to a 
   random starting position and direction */

static void init_penguins()
{
  int i;
  for (i = 0; i < NUM_PENGUINS; i++)
    {
      penguins[i].x = rand() % screen->w;
      penguins[i].y = rand() % screen->h;
      penguins[i].dx = (rand() % (MAX_SPEED * 2)) - MAX_SPEED;
      penguins[i].dy = (rand() % (MAX_SPEED * 2)) - MAX_SPEED;
    }
}

/* This routine moves each penguin by it's motion vector */
static void move_penguins()
{
  int i;
  for (i = 0; i < NUM_PENGUINS; ++i)
    {
      // move the penguin by it's motion vector
      penguins[i].x += penguins[i].dx;
      penguins[i].y += penguins[i].dy;

      // turn the penguin around if it hits the edge of the screen
      if (penguins[i].x < 0 || penguins[i].x > screen->w - 1)
	penguins[i].dx = -penguins[i].dx;
      if (penguins[i].y < 0 || penguins[i].y > screen->h - 1)
	penguins[i].dy = -penguins[i].dy;
    }
}

/* This routine draws each penguin to the screen surface */
static void draw_penguins()
{
  int i;
  SDL_Rect src, dest;

  for (i = 0; i < NUM_PENGUINS; ++i)
    {
      src.x = 0;
      src.y = 0;
      src.w = penguin->w;
      src.h = penguin->h;

      /* The penguin's position specifies its centre. We subtract half of 
	 its width and height to get its upper left corner */

      dest.x = penguins[i].x - penguin->w / 2;
      dest.y = penguins[i].y - penguin->h / 2;
      dest.w = penguin->w;
      dest.h = penguin->h;
      SDL_BlitSurface(penguin, &src, screen, &dest);
    }
}

int main()
{
  SDL_Surface *temp;
  SDL_Surface *background;
  SDL_Rect src, dest;
  int frames;

  // Initialise SDL and test for errors
  if (SDL_Init(SDL_INIT_VIDEO) != 0)
    {
      printf("Unable to initialise SDL: %s\n", SDL_GetError());
      exit(EXIT_FAILURE);
      // PFJ - Changed from return 1;
    }

  // Ensure SDL_Quit is called on termination
  atexit(SDL_Quit);

  // Attempt to set a 640 x 480 hicolor mode with double buffer
  screen = SDL_SetVideoMode(640, 480, 16, SDL_DOUBLEBUF);
  if (screen == NULL)
    {
      printf("Unable to set video mode: %s\n", SDL_GetError());
      exit(EXIT_FAILURE);
      // PFJ - Changed from return 1;
    }

  /* Load the background image and convert it to the display's pixel format. 
     This conversion will drastically improve the performance of 
     SDL_BlitSurface, as it will not have to convert the surface on the fly */
  temp = SDL_LoadBMP("background.bmp");
  background = SDL_DisplayFormat(temp);
  if (background == NULL)
    {
      printf("Unable to load background image\n");
      exit(EXIT_FAILURE);
      // Both lines changed for correct exit and descriptive error message
    }

  // Free the temp surface as we don't need it now
  SDL_FreeSurface(temp);

  // Load penguin image
  temp = SDL_LoadBMP("smallpenguin.bmp");
  if (temp == NULL)
    {
      printf("Unable to load the penguin\n");
      exit(EXIT_FAILURE);
      // Both lines changed for correct exit and descriptive error message
    }

  /* Set the penguin's colorkey. Ask for RLE acceleration, a technique that 
     can significantly speed up colorkey blits */

  SDL_SetColorKey(temp, SDL_SRCCOLORKEY | SDL_RLEACCEL, 
		  (Uint16) SDL_MapRGB(temp->format, 0, 0, 255));


  /* Convert the penguin to the display's format. We do this after we set the 
     colorkey, since colorkey blits can sometimes be optimised for a 
     particular display */

  penguin = SDL_DisplayFormat(temp);
  if (penguin == NULL)
    {
      printf("Unable to convert the penguin bitmap\n");
      exit(EXIT_FAILURE);
      // Both lines changed for correct exit and descriptive error message
    }

  // Free temp surface - it's now stored in penguin
  SDL_FreeSurface(temp);

  // Initialise the penguin position data
  init_penguins();

  // Animate 300 frames [about 10 seconds]
  for (frames = 0; frames < 600; ++frames)
    {
      // Draw the background image
      src.x = 0;
      src.y = 0;
      src.w = background->w;
      src.h = background->h;
      dest = src;
      SDL_BlitSurface(background, &src, screen, &dest);

      // Put the penguins on the screen
      draw_penguins();

      // Ask SD: to swap the back buffer to the screen
      SDL_Flip(screen);

      // Move the penguins for the next frame
      move_penguins();
    }

  // Free the memory
  SDL_FreeSurface(background);
  SDL_FreeSurface(penguin);
}

